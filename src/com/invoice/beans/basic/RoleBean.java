package com.invoice.beans.basic;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.invoice.dbacces.RoleDAO;
import com.invoice.dbacces.UserDAO;
@ManagedBean(name="roleBean")
@ViewScoped
public class RoleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idRole;
	private String Name;
	private boolean addInvoice;
	private boolean viewInvoice;
	private boolean addProduct;
	private boolean viewProduct;
	private boolean addReciept;
	private boolean viewReciept;
	private boolean addBuyInvoice;
	private boolean viewBuyInvoice;
	private boolean addRole;
	private boolean viewRole;
	private boolean addUser;
	private boolean viewUser;
	private boolean addWarehause;
	private boolean viewWarehause;
	private boolean addDelivery;
	private boolean viewDelivery;
	private boolean addContractor;
	private boolean viewContractor;
	private FacesMessage message;
	
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public boolean isAddInvoice() {
		return addInvoice;
	}
	public void setAddInvoice(boolean addInvoice) {
		this.addInvoice = addInvoice;
	}
	public boolean isAddProduct() {
		return addProduct;
	}
	public void setAddProduct(boolean addProduct) {
		this.addProduct = addProduct;
	}
	public boolean isAddReciept() {
		return addReciept;
	}
	public void setAddReciept(boolean addReciept) {
		this.addReciept = addReciept;
	}
	public boolean isAddBuyInvoice() {
		return addBuyInvoice;
	}
	public void setAddBuyInvoice(boolean addBuyInvoice) {
		this.addBuyInvoice = addBuyInvoice;
	}
	public boolean isViewInvoice() {
		return viewInvoice;
	}
	public void setViewInvoice(boolean viewInvoice) {
		this.viewInvoice = viewInvoice;
	}
	public boolean isViewWarehause() {
		return viewWarehause;
	}
	public void setViewWarehause(boolean viewWarehause) {
		this.viewWarehause = viewWarehause;
	}
	public boolean isViewProduct() {
		return viewProduct;
	}
	public void setViewProduct(boolean viewProduct) {
		this.viewProduct = viewProduct;
	}
	public boolean isViewBuyInvoice() {
		return viewBuyInvoice;
	}
	public void setViewBuyInvoice(boolean viewBuyInvoice) {
		this.viewBuyInvoice = viewBuyInvoice;
	}
	public boolean isViewDelivery() {
		return viewDelivery;
	}
	public void setViewDelivery(boolean viewDelivery) {
		this.viewDelivery = viewDelivery;
	}
	public boolean isAddContractor() {
		return addContractor;
	}
	public void setAddContractor(boolean addContractor) {
		this.addContractor = addContractor;
	}
	public boolean isViewContractor() {
		return viewContractor;
	}
	public void setViewContractor(boolean viewContractor) {
		this.viewContractor = viewContractor;
	}
	public boolean isAddUser() {
		return addUser;
	}
	public void setAddUser(boolean addUser) {
		this.addUser = addUser;
	}
	public boolean isViewUser() {
		return viewUser;
	}
	public void setViewUser(boolean viewUser) {
		this.viewUser = viewUser;
	}
	public boolean isAddRole() {
		return addRole;
	}
	public void setAddRole(boolean addRole) {
		this.addRole = addRole;
	}
	public boolean isViewRole() {
		return viewRole;
	}
	public void setViewRole(boolean viewRole) {
		this.viewRole = viewRole;
	}
	public boolean isViewReciept() {
		return viewReciept;
	}
	public void setViewReciept(boolean viewReciept) {
		this.viewReciept = viewReciept;
	}
	public boolean isAddWarehause() {
		return addWarehause;
	}
	public void setAddWarehause(boolean addWarehause) {
		this.addWarehause = addWarehause;
	}
	public boolean isAddDelivery() {
		return addDelivery;
	}
	public void setAddDelivery(boolean addDelivery) {
		this.addDelivery = addDelivery;
	}
	
	public FacesMessage getMessage() {
		return message;
	}
	public void setMessage(FacesMessage message) {
		this.message = message;
	}
	public void insertRole() throws Exception
	{
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		if(RoleDAO.insertRole(this))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sukces", "Zmiany zosta³y zapisane.");
			context.addMessage(null, message);
		}
		else
		{
			context.addMessage("Test-Error", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error-Summary", "Error-Detail"));
		      
		}
	}
	
	public boolean deleteRole() throws SQLException
	{

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		if(idRole == 1)
		{
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Zabronione.", "Nie mozna usuwaæ roli szefa"));
			return false;
		}
		if(UserDAO.getUsers(this).size()>0)
		{
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Zabronione.", "Nie mozna usuwaæ u¿ywanej roli"));
			return false;
		}
		if(!RoleDAO.deleteRole(this))
		{
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d", "Zmiany nie zosta³y zapisane."));
			return false;
		}
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sukces", "Zmiany zosta³y zapisane."));
		return true;
	}
	public void updateRole()
	{
		System.out.println("update role");
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		if(idRole == 1)
		{
			System.out.println("update role 1");
			setMessage(new FacesMessage(FacesMessage.SEVERITY_WARN, "Zabronione.", "Nie mozna zmieniac roli szefa"));
			return;
		}
		if(!RoleDAO.updateRole(this))
		{
			System.out.println("update role failed");
			setMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d", "Zmiany nie zosta³y zapisane."));
			return;
		}
		System.out.println("update done!");
		setMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Sukces", "Zmiany zosta³y zapisane."));
	}
	
	public void nameValidation(FacesContext context, UIComponent component,
		    Object value) throws ValidatorException, SQLException {
		System.out.println("validation" + value);
		if(value== null || "" == (String)value)
			throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Nazwa Roli nie mo¿e byæ pusta"));
		if(((String)value).length() <= 2)
			throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Nazwa Roli musi mieæ min 3 znaki"));
		if(value != null && RoleDAO.getRole((String) value) != null) 
			throw new ValidatorException(
		                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "Nazwa Roli Musi Byæ Unikalna"));
		
	}
}
