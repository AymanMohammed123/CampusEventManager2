// This class represents an academic department.
public class AcademicDepartment {
    // These fields store the department name and the responsible person.
    private final String departmentName;
    private final Person responsiblePerson;

    // This constructor sets the department data.
    public AcademicDepartment(String departmentName, Person responsiblePerson) {
        this.departmentName = departmentName;
        this.responsiblePerson = responsiblePerson;
    }

    // Returns the department name.
    public String getDepartmentName() {
        return departmentName;
    }

    // Returns the person responsible for the department.
    public Person getResponsiblePerson() {
        return responsiblePerson;
    }
}
