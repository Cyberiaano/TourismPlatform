package com.example.ProjetIntegre.DTO;

import com.example.ProjetIntegre.entities.Anonnce;
import com.example.ProjetIntegre.entities.Organisation;
import com.example.ProjetIntegre.entities.Publication;
import com.example.ProjetIntegre.entities.Utilisateur;

public class Ann {
    private Organisation organisation;
    private Anonnce anonnce;

    public Organisation getOrganisation() {
        return this.organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public Anonnce getAnonnce() {
        return this.anonnce;
    }

    public void setAnonnce(Anonnce anonnce) {
        this.anonnce = anonnce;
    }

    public Ann(Organisation organisation, Anonnce anonnce) {
        this.organisation = organisation;
        this.anonnce = anonnce;
    }
}
