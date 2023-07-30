package com.scaleupindia;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.enums.PetType;
import com.scaleupindia.service.OwnerService;
import com.scaleupindia.service.impl.OwnerServiceImpl;
import com.scaleupindia.util.InputUtil;

/**
 * @author abhishekvermaa10
 *
 */
public class Demo {
	private OwnerService ownerService;

	public static void main(String[] args) throws IOException {
		Demo demo = new Demo();
		demo.run(args);
	}

	public void run(String... args) {
		ownerService = new OwnerServiceImpl();
		try (Scanner scanner = new Scanner(System.in)) {
			do {
				System.out.println("Welcome to Petistaan");
				int menuOption = InputUtil.acceptMenuOption(scanner);
				switch (menuOption) {
				case 1:
					PetType petType = InputUtil.acceptPetTypeToOperate(scanner);
					List<OwnerDTO> ownerDTOList = ownerService.findOwners(petType);
					System.out.println("There are " + ownerDTOList.size() + " owners with pet type as " + petType);
					ownerDTOList.forEach(System.out::println);
					break;
				case 2:
					petType = InputUtil.acceptPetTypeToOperate(scanner);
					ownerDTOList = ownerService.updatePetDetails(petType, InputUtil.wantToUseCallable(scanner));
					System.out.println("Updated details of " + ownerDTOList.size() + " pets with pet type as " + petType);
					ownerDTOList.forEach(System.out::println);
					break;
				default:
					System.out.println("Invalid option entered.");
				}
			} while (InputUtil.wantToContinue(scanner));
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
}
