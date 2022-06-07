package org.launchcode.techjobs.console;

import java.util.*;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> categoryOptions = new HashMap<>();
        categoryOptions.put("core competency", "Skill");
        categoryOptions.put("employer", "Employer");
        categoryOptions.put("location", "Location");
        categoryOptions.put("position type", "Position Type");
        categoryOptions.put("all", "All");

        // Top-level menu options
        HashMap<String, String> searchOptions = new HashMap<>();
        searchOptions.put("search", "Search");
        searchOptions.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String searchChoice = getUserSelection("View jobs by:", searchOptions);

            if (searchChoice.equals("list")) {

                String chosenOption = getUserSelection("List", categoryOptions);

                if (chosenOption.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(chosenOption);

                    System.out.println("\n*** All " + categoryOptions.get(chosenOption) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchCategory = getUserSelection("Search by:", categoryOptions);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();

                if (searchCategory.equals("all")) {
                    printJobs(JobData.findByValue(searchTerm));
                } else {
                    printJobs(JobData.findByColumnAndValue(searchCategory, searchTerm));
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while(!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        //iterate over an ArrayList of jobs (allJobs)
        //create nested loop that loops over each hashmap so that keys can update without needing to update this method
        String positionType;
        String name;
        String employer;
        String location;
        String coreCompetency;

        if (someJobs.size() == 0 || someJobs == null) {
            System.out.println("No Results");
        } else {
            for (HashMap<String, String> i : someJobs) {
                System.out.println("\n*****");
                for (Map.Entry<String, String> j : i.entrySet()) {
                    String title = j.getKey();
                    String job = j.getValue();
                    System.out.println(title + ": " + job);
                }
                System.out.println("*****");
            }
        }
    }
}
