package br.com.polypus.jb;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

public abstract class BeanBase {

    private String acao;
    //Msg de retorno ao usuário
    private String msg;
    //0-Erro 1-Notificacao;
    private int tipoMsg;
    private PageContext pageContext;

    public abstract void pageLoad();

    public void redirectCompleto(String url) throws IOException {
        //Muda a requisição
        HttpServletResponse response = (HttpServletResponse) getPageContext().getResponse();
        response.sendRedirect(url);
    }

    public void redirect(String url) throws ServletException, IOException {
        //Continua na mesma requisição
        //Entre páginas que estão na mesma aplicação
        getPageContext().forward(url);
    }

    private String primeiraLetraMaiuscula(String nome) {
        String ch = nome.substring(0, 1);
        String texto = nome.substring(1);
        StringBuffer temp = new StringBuffer();
        temp.append(ch.toUpperCase()).append(texto);
        return temp.toString();
    }

    private void defineAtributoDinamico(String nome, byte[] valor) {
        try {
            StringBuffer sb = new StringBuffer("set");
            sb.append(primeiraLetraMaiuscula(nome));
            Class tipo = this.getClass().getDeclaredField(nome).getType();

            Class[] tipoParam = {tipo};
            Method metodo = this.getClass().getMethod(sb.toString(), tipoParam);
            Object[] tipoValores = {converter(valor, tipo)};
            metodo.invoke(this, tipoValores);
            //setUsuTxNome(tpo nome)

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object converter(byte[] valor, Class tipo) throws Exception {
        if (String.class == tipo) {
            return new String(valor);
        } else if (Integer.class == tipo) {
            return Integer.parseInt(new String(valor));
        } else if (Float.class == tipo) {
            return Float.parseFloat(new String(valor));
        } else if (Double.class == tipo) {
            return Double.parseDouble(new String(valor));
        } else if (Long.class == tipo) {
            return Long.parseLong(new String(valor));
        } else if (Byte.class == tipo) {
            return Byte.parseByte(new String(valor));
        } else if (Short.class == tipo) {
            return Short.parseShort(new String(valor));
        } else {
            throw new Exception("Tipo não suportado: " + tipo);
        }
    }

    private void defineAtributoDinamicoBinario(String nome, byte[] valor) {
        try {
            StringBuffer sb = new StringBuffer("set");
            sb.append(primeiraLetraMaiuscula(nome));
            Class[] tipoParam = {byte[].class};
            Method metodo = this.getClass().getMethod(sb.toString(), tipoParam);
            Object[] tipoValores = {valor};
            metodo.invoke(this, tipoValores);
            //setUsuTxNome(tpo nome)

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readFormUpload(RequestContext rc) throws Exception {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List items = upload.parseRequest(rc);
        Iterator iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            String nome = item.getFieldName();
            byte[] valor = item.get();
            if ("acao".equals(nome)) {
                setAcao(new String(valor));
            } else {
                defineAtributo(nome, valor);
            }
        }
    }

    private void readFormUploadDinamico(RequestContext rc) throws Exception {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List items = upload.parseRequest(rc);
        Iterator iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            String nome = item.getFieldName();
            byte[] valor = item.get();
            if (item.isFormField()) {
                if ("acao".equals(nome)) {
                    setAcao(new String(valor));
                } else {
                    defineAtributoDinamico(nome, valor);
                }
            } else {
                defineAtributoDinamicoBinario(nome, valor);
            }
        }
    }

    public void defineAtributo(String nome, byte[] valor) {
    }

    public String getExecute() {
        this.pageLoad();
        RequestContext rc = new ServletRequestContext((HttpServletRequest) getPageContext().getRequest());
        boolean isMultiPart = FileUpload.isMultipartContent(rc);
        if (isMultiPart) {
            try {
                //readFormUpload(rc);
                readFormUploadDinamico(rc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (acao != null && acao.trim().length() > 0) {
            try {
                Class[] tipos = {};
                Method metodo = this.getClass().getMethod(acao, tipos);
                Object[] valores = {};
                metodo.invoke(this, valores);
            } catch (Exception e) {
                e.printStackTrace();
                msg = "Método ".concat(acao).concat(" não existe!");
                setTipoMsg(0);
            }
        }
        return "";
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTipoMsg() {
        return tipoMsg;
    }

    public void setTipoMsg(int tipoMsg) {
        this.tipoMsg = tipoMsg;
    }

    public PageContext getPageContext() {
        return pageContext;
    }

    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }
}
