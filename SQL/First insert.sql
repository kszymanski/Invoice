INSERT 
	INTO role (`Name`,
				`AddInvoice`,
				`ViewInvoice`,
				`AddProduct`,
				`ViewProduct`,
				`AddReciept`,
				`ViewReciept`,
				`AddBuyInvoice`,
				`ViewBuyInvoice`, 
				`AddRole`,
				`ViewRole`,
				`AddContractor`,
				`ViewContractor`,
				`AddUser`,
				`ViewUser`,
				`ViewWarehause`,
				`AddWarehause`,
				`ViewDelivery`,
				`AddDelivery`)
	VALUES (admin, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
INSERT 
	INTO user (`idUser`,
			`Name`,
			`Surname`,
			`idRole`,
			`Password`,
			`Active`,
			`Admin`) 
	VALUES ('admin', 'Admin','Administrator', 1, 'admin', 1,1);