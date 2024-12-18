package Team;

import Window.LoggedInWindow;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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

    public void addCustomSector(String sectorName) {
        if (findSector(sectorName) == null) {
            sectors.add(new CustomSector(sectorName));
            System.out.println("Custom sector '" + sectorName + "' added.");
            FileSystem.saveData(sectors); // Save to file after adding
        } else {
            System.out.println("Sector with this name already exists.");
        }
    }

    public void addMemberToSector(String sectorName, String memberName) {
        Sector sector = findSector(sectorName);
        if (sector != null) {
            sector.addMember(memberName);
            FileSystem.saveData(sectors); // Save to file after adding a member
        } else {
            System.out.println("Sector not found.");
        }
    }

    public void displayAllSectors() {
        for (Sector sector : sectors) {
            sector.displayMembers();
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
}
