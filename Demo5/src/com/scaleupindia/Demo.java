package com.scaleupindia;

import java.io.IOException;
import java.util.Scanner;

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
					ownerService.saveOwnersAutomatically(InputUtil.generateOwners());
					System.out.println("Owners have been saved automatically successfully.");
					break;
				case 2:
					ownerService.saveOwnersManually(InputUtil.generateOwners());
					System.out.println("Owners have been saved manually successfully.");
					break;
				case 3:
					ownerService.saveOwnersManuallyWithSavepoint(InputUtil.generateOwners());
					System.out.println("Owners have been saved manually with savepoint successfully.");
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
