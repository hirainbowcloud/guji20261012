select ofc.officeCode,ofc.city,ofc.phone, 
       emps.employeeNumber,emps.firstName,emps.email,emps.jobTitle
from classicmodels.offices as ofc
inner join classicmodels.employees as emps
on ofc.officeCode=emps.officeCode

select emps.employeeNumber,emps.firstName,emps.email,emps.jobTitle,
       cst.customerNumber,cst.customerName,cst.contactFirstName,cst.phone,cst.city
from classicmodels.employees as emps
inner join classicmodels.customers as cst
on emps.employeeNumber=cst.salesRepEmployeeNumber
order by emps.employeeNumber

set autocommit=0;
INSERT INTO classicmodels.offices
(officeCode,city,phone,addressLine1,addressLine2,state,country,postalCode,territory)
VALUES
('8','Taipei','0223826015','Kung Yuan Road',null,'TW','Taiwan','10001','ASIA' );

-- rollback;
-- set autocommit=1;