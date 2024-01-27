package com.shubham.punch_family_tree;

import com.shubham.punch_family_tree.controller.TreeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PunchFamilyTreeApplication implements CommandLineRunner {

	private final TerminalCommandProcessor commandProcessor;

	public PunchFamilyTreeApplication(TerminalCommandProcessor commandProcessor) {
		this.commandProcessor = commandProcessor;
	}

	public static void main(String[] args) {
		SpringApplication.run(PunchFamilyTreeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		commandProcessor.startCommandLoop();
	}

}
