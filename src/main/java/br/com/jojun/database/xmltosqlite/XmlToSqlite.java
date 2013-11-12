package br.com.jojun.database.xmltosqlite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.com.jojun.database.xmltosqlite.model.ar.Biblia;
import br.com.jojun.database.xmltosqlite.model.ar.Capitulo;
import br.com.jojun.database.xmltosqlite.model.ar.Livro;
import br.com.jojun.database.xmltosqlite.model.ar.Versiculo;

public class XmlToSqlite {
	
	private final static Logger LOGGER = Logger.getLogger(XmlToSqlite.class .getName()); 
	
	private static Connection connection;
	
	public static void main(String[] args) throws SQLException {
		try {
			LOGGER.setLevel(Level.INFO); 
			File file = new File("/home/82728925534/Dropbox/didaque/ar_1.3.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Biblia.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Biblia biblia = (Biblia) jaxbUnmarshaller.unmarshal(file);
			System.out.println(biblia.getTitle());
			String cargaLivros = "";
			String cargaCapitulos = "";
			String cargaVersiculos = "";
			String versao = "Almeida Recebida";
			String idioma = "PT";
			String testamento = "VT";
			
			createDatabase(null, null);
			createTables();
			
			String cargaBiblia = "INSERT into biblia (versao, idioma) VALUES ('"+versao+"', '"+idioma+"')";
			
			
			
			for(int i = 0; i < biblia.getLivros().size(); i++){
				Livro l = biblia.getLivros().get(i);
				LOGGER.info("Livro: "+l.getNome()+" total caps: "+l.getCapitulos().size());
				if(l.getNome().equalsIgnoreCase("mateus"))
					testamento = "AT";
//				cargaLivros += "INSERT into livro (numero, nome, qtd_capitulos, testamento) VALUES ("+i+", '"+l.getNome()+"', "+l.getCapitulos().size()+", '"+testamento+"')\n";
				
				for(Capitulo c : l.getCapitulos()){
					cargaCapitulos += "INSERT into capitulo (numero, qtd_versiculos, livroid) VALUES ("+c.getNumero()+", "+c.getVersiculos().size()+", "+i+")\n";
					LOGGER.info("Capítulo: "+c.getNumero());
					for(Versiculo v : c.getVersiculos()){
						LOGGER.info("Versiculo: "+v.getNumero()+". "+v.getVersiculo());
//						cargaBiblia +="INSERT into versiculo (livro, capitulo, versiculo, texto, testamento, versao, idioma) "
//								+ "VALUES ('"+l.getNome()+"', "+c.getNumero()+", "+v.getNumero()+", '"+v.getVersiculo()+"', '"+testamento+"', '"+versao+"', '"+idioma+"')\n";
						
						cargaVersiculos +="INSERT into versiculos (livro, capitulo, versiculo, texto) "
								+ "VALUES ('"+l.getNome()+"', "+c.getNumero()+", "+v.getNumero()+", '"+v.getVersiculo()+"');\n";

					}
				}
			}
			
			File arSQL = new File("/home/82728925534/Dropbox/didaque/ar.sql");
			try {
				FileWriter fw = new FileWriter(arSQL);
				fw.append(cargaBiblia);
//				fw.append(cargaLivros);
				fw.append(cargaVersiculos);
				
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private static void createTables() throws SQLException {
		Statement st;
		st = connection.createStatement();

		String tableVersiculo = "DROP TABLE IF EXISTS versiculos; CREATE TABLE versiculos (livro TEXT, capitulo INTEGER, versiculo INTEGER, texto TEXT)";
		String tableBiblia = "DROP TABLE IF EXISTS biblias; CREATE TABLE biblias (versao TEXT, idioma TEXT)";
		st.execute(tableVersiculo);
		st.execute(tableBiblia);

		
	}

	public static void createDatabase(String dbName, String outDir){

	    try {
	      Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:/home/82728925534/Dropbox/didaque/ar.sqlite");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	}
	
	public static void execute(String command) throws SQLException{
		Statement statement = connection.createStatement();
		statement.executeUpdate(command);
		
	}
	
	public static String getDBType(String cls){
		String type = null;
		if (cls.equalsIgnoreCase("string")
			|| cls.equalsIgnoreCase("char")
			|| cls.equalsIgnoreCase("character")) {
			type = "TEXT";
		} else if (cls.equalsIgnoreCase("integer")
			|| cls.equalsIgnoreCase("int")
			|| cls.equalsIgnoreCase("long")
			|| cls.equalsIgnoreCase("short")
			|| cls.equalsIgnoreCase("byte")
			|| cls.equalsIgnoreCase("boolean")) {
			type = "INTEGER";
		} else if (cls.equalsIgnoreCase("double")
				|| cls.equalsIgnoreCase("float")) {
			type = "REAL";
		} else if (cls.equalsIgnoreCase("date")) {
			type = "DATE";
		} else {
			type = "TEXT";
		}
		
		return type;
	}
}
