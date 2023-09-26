package com.amdocs.pojos.main;
import java.util.*;

import com.amdocs.dao.DoctorDAO;
import com.amdocs.dao.DoctorDAOImpl;
import com.amdocs.doctormanagement.exception.DoctorNotFoundException;
import com.amdocs.pojos.Doctor;

public class DoctorMenuDriven {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		DoctorDAO dao=new DoctorDAOImpl(); 
		do {
			
			System.out.println("1. Add new doctor\r\n"
					+ "2. Update doctor fees\r\n"
					+ "3. Delete doctor\r\n"
					+ "4. View all doctors\r\n"
					+ "5. Find doctor by specialization\r\n"
					+ "6. Find doctors whose fees is less than all doctors of given shift\r\n"
					+ "7. Count number of doctors by shift\r\n"
					+ "8. Exit\r\n"
					+ "");
			
			System.out.println("Enter your choice:");
			int choice=sc.nextInt();  
			switch(choice)
			{
			case 1:
				
				System.out.println("Enter Doctor Id");
				int doctorId=sc.nextInt();
				System.out.println("Enter doctorName");
				String doctorName=sc.next();
				System.out.println("Enter mobileNumber");
				String mobileNumber=sc.next();
				System.out.println("Enter specialization");
				String specialization=sc.next();
				System.out.println("Enter availableShift");
				String availableShift=sc.next();
				System.out.println("Enter fees");
				double fees=sc.nextDouble();
				Doctor doctor=new Doctor(doctorId,doctorName,mobileNumber,specialization,availableShift,fees);
				int y = dao.addDoctor(doctor);
				System.out.println(y);
				System.out.println("Doctor Added Successfully");
				break;
				
			case 2:
				System.out.println("Update doctor fees");
				System.out.println("Enter doctor ID");
				 int doctorId2=sc.nextInt();
				 System.out.println("Enter fees");
				 double fees2=sc.nextDouble();
				 boolean flag=false;
				 try{
				  flag=dao.updateDoctorFees(doctorId2, fees2);
				 if(flag)
					 System.out.println("Doctor fees updated");
				 else
					 throw new DoctorNotFoundException("Doctor not found"); 
				 }
				 catch(DoctorNotFoundException e){
					 e.printStackTrace(); 
				 }
				break;
				
			case 3:
				System.out.println("Enter the doctor id you want to delete");
				int iddelete = sc.nextInt();
				int x=0; 
				try{
				x = dao.deleteDoctor(iddelete);
				if(x!=0){
					System.out.println("Doctor has been deleted");  
				}
				else{
					throw new DoctorNotFoundException("Doctor not found");   
				}
				} catch(DoctorNotFoundException e){
					e.printStackTrace(); 
				}
				break;
				
			case 4:
				System.out.println("View All doctors");
				List<Doctor> doctorList=dao.showAllDoctors();
				if(doctorList.size()>0){
				for(Doctor doc:doctorList)
				{
					System.out.println(doc);
				}
				}
				else{
					System.out.println("No Doctor Available"); 
				}
				break;
				
			case 5:
				System.out.println("Enter the Specialization for which you want the doctor");
				String sp = sc.next();
				try{
					List<Doctor>doctemp = dao.searchBySpecialization(sp);
					if(doctemp.size()>0){
					for (Doctor doc:doctemp) {
						System.out.println(doc);
					}
					System.out.println("above is the list"); 
					}
					else{
						throw new DoctorNotFoundException("no doctor available for the desired specialization");
					}
					
				}catch(DoctorNotFoundException e){
					e.printStackTrace(); 
				}
				break; 
				
			case 6:
				System.out.println("Enter the shift you want a doctor");
				String shift = sc.next();
				System.out.println("Enter your fees range");
				double fee = sc.nextInt();
				try{
				List<Doctor>docTemp = dao.searchByFeesAndShift(shift, fee);
				if(docTemp.size()>0){
				for(Doctor doc:docTemp) {
					System.out.println(doc);
				}
				System.out.println("above is the list"); 
				}
				else{
					throw new DoctorNotFoundException("there is no doctor whose fees is less than all doctors of given shift");
				}
				
				}
				
				catch(DoctorNotFoundException e){
					e.printStackTrace(); 
				}
				break; 
			case 7:
				System.out.println("Enter the shift you want the count of doctors");
				String shift1 = sc.next();
				int docCount = dao.countDoctorsByShift(shift1);
				System.out.println("The number of doctors currently available are :"+docCount);
				break; 
			
			case 8:
				System.out.println("Exit"); 
				break; 
			}
			
		}
		while(true);

	}

}
