package gr.bundles.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class BundlesApiApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(BundlesApiApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BundlesApiApplication.class, args);
	}

	@RequestMapping(value = "/")
	public String info() {
		return "This is a demo REST API for bundled offers";
	}
	
	@RequestMapping(value = "/bundles", method = RequestMethod.POST)
	public ResponseEntity<Object> createBundle(@RequestBody Bundle bundle){
		Connection conn = null;  
		try {  
            // db parameters  
            String url = "jdbc:sqlite:"+System.getProperty("user.dir")+"/bundles.db";  
            // create a connection to the database  
            conn = DriverManager.getConnection(url);  
              
            String sql = "CREATE TABLE IF NOT EXISTS bundles (\n"
            		+ " productName text NOT NULL,\n"
            		+ " price real NOT NULL,\n"
            		+ " productCode text NOT NULL,\n"
            		+ " productExpirationDate text,\n"
            		+ " availabilityDate text NOT NULL,\n"
            		+ " active integer NOT NULL\n"
            		+ ");";
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);
            stmt.close();
            
            sql = "INSERT INTO bundles VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bundle.getProductName());
            pstmt.setFloat(2, bundle.getPrice());
            pstmt.setString(3, bundle.getProductCode());
            pstmt.setString(4, bundle.getProductExpirationDate());
            pstmt.setString(5, bundle.getAvailabilityDate());
            pstmt.setBoolean(6, bundle.isActive());
            
            pstmt.executeUpdate();
            
            //Close Connection
            pstmt.close();
            conn.close();
              
        } catch (SQLException e) {  
        	new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); 
        }
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
		
	}
	@RequestMapping(value = "/bundles")
	public ResponseEntity<Object> getBundles(){
		
		ArrayList<Bundle> bundles = new ArrayList<Bundle>();
		
		Connection conn = null;  
		try {  
            // db parameters  
            String url = "jdbc:sqlite:"+System.getProperty("user.dir")+"/bundles.db";  
            // create a connection to the database  
            conn = DriverManager.getConnection(url);  
              
            String sql = "SELECT * FROM bundles";
            Statement stmt = conn.createStatement();  
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
            	Bundle bundle = new Bundle(rs.getString("productName"),rs.getFloat("price"),
            			rs.getString("productCode"),rs.getString("productExpirationDate"),
            			rs.getString("availabilityDate"),rs.getBoolean("active"));
            	bundles.add(bundle);
            }
            
            
            //Close Connection
            rs.close();
            stmt.close();
            conn.close();
            
            return new ResponseEntity<>(bundles, HttpStatus.OK);
              
        } catch (SQLException e) {  
        	new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); 
        }
		return new ResponseEntity<>(bundles, HttpStatus.OK);
	}
	
}
