package tn.esprit.pitwin.ressource.util;

public enum Roles {
	
    CANDIDATE("Candidate"),
    RECRUITMENT_MANAGER("RecruitmentManager"),
    CHIEF_HUMAN_RESOURCES_OFFICER("ChiefHumanResourcesOfficer"),
    ADMINISTRATOR("Administrator"),
    ANONYMOUS("Anonymous");

    private final String role;

    Roles(String value) {
        this.role = value;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
