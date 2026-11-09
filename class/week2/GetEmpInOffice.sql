Delimiter //
Create procedure  classicmodels.GetEmpInOffice
(
  cityGetEmpInOfficename varchar(50)    

)

BEGIN
    	
    
    select *	
    from  classicmodels.employees
    where employees.officecode=(select officecode from classicmodels.offices where city=cityname) ;

END //
