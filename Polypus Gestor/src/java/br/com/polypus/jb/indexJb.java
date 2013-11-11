package br.com.polypus.jb;

import br.com.polypus.bo.UsuarioBo;
import br.com.polypus.transfer.Usuario;

public class indexJb extends BeanBase{

    private String email;
    private String senha;
    
    public static final String USUARIO_LOGADO = "usuarioLogado";
    
    @Override
    public void pageLoad() {
    
    }
    
    public void logar(){
        try {
            Usuario usuario = new Usuario();
            usuario.setEmail(getEmail());
            usuario.setSenha(getSenha());
            UsuarioBo usuarioBo = new UsuarioBo();
            usuario = usuarioBo.logar(usuario);
            if(usuario.getId() != 0){
                getPageContext().getSession().setAttribute(USUARIO_LOGADO, usuario);
                redirectCompleto("redirecionar.jsp");
            }else{
                setMsg("OPS... Aconteceu algum erro.");
                setTipoMsg(0);
            }
        } catch (Exception e) {
            setMsg(e.getMessage());
            setTipoMsg(0);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
