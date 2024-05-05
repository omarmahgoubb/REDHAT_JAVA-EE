package model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String email;
    private String username;
    @NotNull
    private String password;
    private String name;
    private String role;

    @ManyToMany
    private List<Board> collaboratorBoards = new ArrayList<>();

	
	public int getId() 
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getName() 
	{
		return name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public User(){}
	
	public User(String email, String password)
	{
		this.email=email;
		this.password =password;
	}
	public User(String email, String password,String name)
	{
		this.email=email;
		this.password =password;
		this.name=name;
	}
	
	public User(String email, String password,String name,String role)
	{
		this.email=email;
		this.password =password;
		this.name=name;
		this.role=role;
	}
}