package Team;

import Window.LoggedInWindow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import static Global.GlobalData.YELLOW_TEXT;
import static Global.GlobalMethod.printCentered;

public class TeamManager {
    private List<Sector> sectors;

    public TeamManager() {
        sectors = FileSystem.loadData();  // Load data from file, if available

        // If no data is loaded, add predefined sectors
        if (sectors == null) {
            sectors = new ArrayList<>();
            sectors.add(new DevelopmentSector());
            sectors.add(new LogisticsSector());
            sectors.add(new CommunicationSector());
            sectors.add(new CreativeSector());
        }
    }
    public void removeMemberFromSector(int sectorIndex, String memberName, int eventID) {
        if (sectorIndex >= 0 && sectorIndex < sectors.size()) {
            Sector sector = sectors.get(sectorIndex);
            if (sector.getTeamMembers().remove(memberName)) {
                System.out.println(memberName + " removed from " + sector.getSectorName());
                FileSystem.saveData(sectors, eventID); // Save to file after removing a member
            } else {
                System.out.println("Member not found in the sector.");
            }
        } else {
            System.out.println("Invalid sector index.");
        }
    }

    public void addCustomSector(String sectorName, int eventID) {
        if (findSector(sectorName) == null) {
            sectors.add(new CustomSector(sectorName));
            System.out.println("Custom sector '" + sectorName + "' added.");
            FileSystem.saveData(sectors, eventID);
        } else {
            System.out.println("Sector with this name already exists.");
        }
    }

    public void addMemberToSector(int sectorIndex, String memberName, int eventID) {
            Sector sector = sectors.get(sectorIndex);
            sector.addMember(memberName);
            FileSystem.saveData(sectors, eventID);
    }
    public boolean choiceValidity(int choice) {
        if(choice >= 0 && choice < sectors.size()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void displaySectorMembers(int sectorIndex, int eventID) {
        Sector sector = sectors.get(sectorIndex);
        sector.displayMembers();
    }

    public static boolean findEventByID(int eventIDInput) {
        String StringEventIDInput = String.valueOf(eventIDInput);

        String filePath = "Event_Data.txt";
        List<String[]> events = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] eventDetails = line.split(":");
                events.add(eventDetails);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        for (String[] event : events) {
            if(event[0].equals(StringEventIDInput)) {
                return true;
            }
            else {
                return false;
            }
        }

        return false;
    }

    public void displaySectorNames() {
        printCentered("+" + "-".repeat(7) + "+" + "-".repeat(50) + "+");
        printCentered("|" + "  " + "No.  " + "|" + " ".repeat(19) + "Sector Name" + " ".repeat(20) + "|");
        printCentered("+" + "-".repeat(7) + "+" + "-".repeat(50) + "+");
        for (int i = 0; i < sectors.size(); i++) {
            if(sectors.get(i).getSectorName().length() % 2 == 0) {
                printCentered("|" + "   " + (i + 1) + "   " + "|" + " ".repeat((50 - sectors.get(i).getSectorName().length()) / 2) + sectors.get(i).getSectorName() + " ".repeat((50 - sectors.get(i).getSectorName().length()) / 2) + "|");
                printCentered("+" + "-".repeat(7) + "+" + "-".repeat(50) + "+");
            }
            else {
                printCentered("|" + "   " + (i + 1) + "   " + "|" + " ".repeat((50 - sectors.get(i).getSectorName().length()) / 2) + sectors.get(i).getSectorName() + " ".repeat(((50 - sectors.get(i).getSectorName().length()) / 2) + 1) + "|");
                printCentered("+" + "-".repeat(7) + "+" + "-".repeat(50) + "+");
            }
        }
    }



    private Sector findSector(String sectorName) {
        for (Sector sector : sectors) {
            if (sector.getSectorName().equalsIgnoreCase(sectorName)) {
                return sector;
            }
        }
        return null;
    }

    public int getSectorCount() {
        return sectors.size();
    }
    public String getSectorNameAt(int index) {
        if (index >= 0 && index < sectors.size()) {
            return sectors.get(index).getSectorName();
        }
        return null;
    }

}



