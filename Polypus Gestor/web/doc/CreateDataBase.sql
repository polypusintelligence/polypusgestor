IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'Polypus')
	DROP database [Polypus]

GO

CREATE database [Polypus] ON (name = N'Polypus_Data', filename = N'C:\polypus\DB\MDF\Polypus_Data.MDF' , SIZE = 10, filegrowth = 10%) 
					LOG	ON (name = N'Polypus_Log', filename = N'C:\polypus\DB\LOG\Polypus_Log.LDF'  , SIZE = 01, filegrowth = 10%)
 COLLATE SQL_Latin1_General_CP1_CI_AI
