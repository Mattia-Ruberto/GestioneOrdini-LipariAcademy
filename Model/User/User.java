package User;

public class User {
	private Integer id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	private Integer id_role;	
	
	
	public User(Integer id, String nome, String cognome, String username, String password, String email, Integer id_role) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.email = email;
		this.id_role = id_role;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getId_role() {
		return id_role;
	}


	public void setId_role(Integer id_role) {
		this.id_role = id_role;
	}



	
	
	
	
	
	
	
	

}
