package com.invoice.control;

import java.util.StringTokenizer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.invoice.beans.basic.RoleBean;
@FacesConverter(value = "roleConverter")
public class RoleConverter implements Converter{
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent ui, String value) {
		 System.out.println("convert");
		if (value == null || (value.trim().length() == 0))
        {
            return value;
        }
		RoleBean role = new RoleBean();
		boolean conversionError = false;
		int hyphenCount = 0;
        StringTokenizer hyphenTokenizer = new StringTokenizer(value, "-");
        while (hyphenTokenizer.hasMoreTokens())
        {
            String token = hyphenTokenizer.nextToken();
            try
            {
                if (hyphenCount == 0) role.setIdRole(Integer.parseInt(token));
                if (hyphenCount == 1) role.setName(token);
                if (hyphenCount == 2) role.setAddBuyInvoice(Boolean.parseBoolean(token));
                if (hyphenCount == 3) role.setAddContractor(Boolean.parseBoolean(token));
                if (hyphenCount == 4)role.setAddInvoice(Boolean.parseBoolean(token));
                if (hyphenCount == 5)role.setAddProduct(Boolean.parseBoolean(token));
                if (hyphenCount == 6)role.setAddReciept(Boolean.parseBoolean(token));
                if (hyphenCount == 7)role.setAddRole(Boolean.parseBoolean(token));
                if (hyphenCount == 8)role.setAddUser(Boolean.parseBoolean(token));
                if (hyphenCount == 9)role.setViewBuyInvoice(Boolean.parseBoolean(token));
                if (hyphenCount == 10)role.setViewContractor(Boolean.parseBoolean(token));
                if (hyphenCount == 11)role.setViewDelivery(Boolean.parseBoolean(token));
                if (hyphenCount == 12)role.setViewInvoice(Boolean.parseBoolean(token));
                if (hyphenCount == 13)role.setViewProduct(Boolean.parseBoolean(token));
                if (hyphenCount == 14)role.setViewReciept(Boolean.parseBoolean(token));
                if (hyphenCount == 15)role.setViewRole(Boolean.parseBoolean(token));
                if (hyphenCount == 16)role.setViewUser(Boolean.parseBoolean(token));
                if (hyphenCount == 17)role.setViewWarehause(Boolean.parseBoolean(token));
                hyphenCount ++;
            }
            catch (Exception exception)
            {
                conversionError = true;
            }
        }
 
        if (conversionError || (hyphenCount != 18))
        {
            throw new ConverterException();
        }
		return role;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent ui, Object value) {
		System.out.println("convert");
		RoleBean role=null;
		if (value instanceof RoleBean)
		{
			role=(RoleBean)value;
			StringBuilder roleString = new StringBuilder();
			roleString.append(role.getIdRole()+"-");
			roleString.append(role.getName()+"-");
			roleString.append(role.isAddBuyInvoice()+"-");
			roleString.append(role.isAddContractor()+"-");
			roleString.append(role.isAddInvoice()+"-");
			roleString.append(role.isAddProduct()+"-");
			roleString.append(role.isAddReciept()+"-");
			roleString.append(role.isAddRole()+"-");
			roleString.append(role.isAddUser()+"-");
			roleString.append(role.isViewBuyInvoice()+"-");
			roleString.append(role.isViewContractor()+"-");
			roleString.append(role.isViewDelivery()+"-");
			roleString.append(role.isViewInvoice()+"-");
			roleString.append(role.isViewProduct()+"-");
			roleString.append(role.isViewReciept()+"-");
			roleString.append(role.isViewRole()+"-");
			roleString.append(role.isViewUser()+"-");
			roleString.append(role.isViewWarehause());
			return roleString.toString();
		}
		return null;
	}

}
