package com.invoice.beans.basic;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import com.invoice.dbacces.RoleDAO;
import com.invoice.dbacces.UserDAO;

@ManagedBean(name="user")
@ViewScoped
public class UserBean implements Serializable{
	//fields
	private static final long serialVersionUID = 1L;
	protected String idUser;
	protected String name;
	protected String surname;
	protected RoleBean role;
	protected String password;
	protected boolean active;
	protected boolean admin;
	protected FacesMessage message;
	protected boolean error=false;
	//Constructors
	public UserBean(){};

	// getters Setters
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public RoleBean getRole() {
		return role;
	}
	public void setRole(RoleBean role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	// navigation methods
	public String viewUser() throws IOException
	{
		System.out.println("redirect");
		FacesContext.getCurrentInstance().getExternalContext().redirect("./UserList?id="+idUser);
		return null;
	}
	public String editUser() throws IOException
	{
		System.out.println("redirect");
		FacesContext.getCurrentInstance().getExternalContext().redirect("./UserList?id="+idUser+"&edit=true");
		return null;
	}
	//refresh user method before View
	public void start() throws SQLException
	{
		System.out.println("start");
		HttpServletRequest request=(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String id = request.getParameter("id");
		if(id == null && role == null)
		{
			role=RoleDAO.getRole(1);
			return;
		}
		if( idUser==null || idUser == "")
		{
			idUser = id;
			UserDAO.getUser(this);
		}
	}
	//Reload data from database
	public void reload() throws SQLException
	{
		System.out.println("reload");
		if(message != null )
		{
			 FacesContext.getCurrentInstance().addMessage(null, message);
			 
		}
		if(!error)UserDAO.getUser(this);
		error = false;
		message = null;
	}
	//Changing role base on role name
	public void rolechange()
	{
		System.out.println("role change");
		role = RoleDAO.getRole(role.getName());
	}
	//Compare defoult fields
	private int isEqual(UserBean user)
	{
		int equal = 0;
		if(!this.idUser.equalsIgnoreCase(user.getIdUser()))equal ++;
		if(!this.name.equalsIgnoreCase(user.getName())) equal ++;
		if(!this.surname.equalsIgnoreCase(user.getSurname()))equal++;
		if(this.role.getIdRole()!= user.role.getIdRole())equal++;
		if(this.active!= user.isActive())equal++;
		return equal;
	}
	//SQL methods
	public void updateUser() throws SQLException
	{
		int toUpdate = isEqual(UserDAO.getUser(idUser));
		if(admin && (!active || role.getIdRole() != 1))
		{
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Zabronione.", "Nie mozna zmieniac roli ani deaktywowaæ konta szefa");
			return;
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		if(toUpdate == 0)
		{
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Nothing to update.", "Nie by³o zmian");
			return;
		}
		if(!UserDAO.updateUser(this))
			{
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d", "Zmiany nie zosta³y zapisane.");
				return;
			}
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sukces", "Zmiany zosta³y zapisane.");
	}
	public void updateUserPassword() throws SQLException
	{

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);

		if(!UserDAO.updateUserPassword(this))
			{
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d", "Zmiany nie zosta³y zapisane."));
				return;
			}
		context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Sukces", "Zmiany zosta³y zapisane."));
	}
	public void insertUser() throws SQLException, IOException
	{
		System.out.println("insert");
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		if(UserDAO.getUser(idUser) == null)
		{
			if(UserDAO.insertUser(this))
			{
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sukces", "Zmiany zosta³y zapisane.");
				context.addMessage(null, message);
				context.getExternalContext().redirect("./UserList?id="+idUser);
			}
			else
			{
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Coœ posz³o nie tak");
			}
		}
	}
	public void deleteUser() throws SQLException, IOException
	{
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Niestety", "To nie zosta³o jeszcze zaimplementowane.");
		context.addMessage(null, message);
			
	}
	//Validator for username
	public void idValidation(FacesContext context, UIComponent component,
		    Object value) throws ValidatorException, SQLException {
		System.out.println("validation" + value);
		if(value== null || "" == (String)value)
			throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Nazwa U¿ytkownika nie mo¿e byæ pusta"));
		if(((String)value).length() <= 2)
			throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Nazwa U¿ytkownika musi mieæ min 3 znaki"));
		if(value != null && UserDAO.getUser((String) value) != null) 
			throw new ValidatorException(
		                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Nazwa U¿ytkownika Musi Byæ Unikalna"));
		
	}
}
