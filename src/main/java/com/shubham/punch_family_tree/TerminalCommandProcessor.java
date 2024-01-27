package com.shubham.punch_family_tree;

import com.shubham.punch_family_tree.controller.QueryController;
import com.shubham.punch_family_tree.controller.TreeController;
import com.shubham.punch_family_tree.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TerminalCommandProcessor {

    private final TreeController treeController;
    private final QueryController queryController;

    public TerminalCommandProcessor(TreeController treeController, QueryController queryController)
    {
        this.treeController = treeController;
        this.queryController = queryController;
    }

    public void startCommandLoop() {
        Scanner scanner = new Scanner(System.in);
        String inputLine;

        System.out.println("Family Tree Application. Type 'exit' to quit.");

        while (true) {
            System.out.print("> ");
            inputLine = scanner.nextLine();

            if (Constants.EXIT.equalsIgnoreCase(inputLine)) {
                break;
            }
            processInput(inputLine);
        }
    }

    private void processInput(String input) {
        String[] args = input.split(" ");
        if (args.length > 2) {
            String command = args[0];

            if (Constants.FAMILY_TREE.equals(command)) {
                String action = args[1];

                if (Constants.ADD.equals(action)) {
                    if("relationship".equals(args[2])){
                        String type = args[3];
                        System.out.print(treeController.addRelationshipType(type));
                    }
                    else {
                        String firstName = args[3];
                        String lastName = args[4];
                        String gender = "M";
                        if(args.length == 6)
                            gender = args[5];
                        System.out.print(treeController.addPerson(firstName + " " + lastName, gender));
                    }
                }
                else if(Constants.CONNECT.equals(action) && args.length > 7){
                    String firstName = args[2], lastName = args[3];
                    String person1Name = firstName + " " + lastName;
                    String type = args[5];
                    firstName = args[7]; lastName = args[8];
                    String person2Name = firstName + " " + lastName;
                    System.out.print(treeController.addPersonsAndRelationship(person1Name,person2Name,type));
                }

                else if(Constants.COUNT.equals(action))
                {
                    String firstName = args[4];
                    String lastName = args[5];
                    if(args[2].equals("sons"))
                        System.out.print(queryController.countOfSons(firstName + " " + lastName));

                    else if(args[2].equals("daughters"))
                        System.out.print(queryController.countOfDaughters(firstName + " " + lastName));

                    else if(args[2].equals("wives"))
                        System.out.print(queryController.countOfWives(firstName + " " + lastName));

                }

                else if(Constants.FATHER.equals(action))
                {
                    System.out.print(queryController.fatherName(args[3] + " " + args[4]));
                }
                else
                {
                    System.out.println("Not supported yet");
                }
            }
        } else {
            System.out.println("Not enough arguments provided.");
        }
    }

}
