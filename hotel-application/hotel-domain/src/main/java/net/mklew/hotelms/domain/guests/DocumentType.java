package net.mklew.hotelms.domain.guests;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12 time 8:44 PM
 */
public enum DocumentType
{
    DRIVER_LICENSE("driverLicense"), PASSPORT_ID("passport"), PERSONAL_ID("personalID"), OTHER(
                    "other");

    private String name;

    public String getName()
    {
        return name;
    }

    private DocumentType(String name)
    {
        this.name = name;
    }

    public static DocumentType fromString(String documentType)
    {
        for(DocumentType type : DocumentType.values())
        {
            if(type.getName().equals(documentType))
            {
                return type;
            }
        }
        throw new RuntimeException("DocumentType from string: " + documentType
            + " cannot be resolved." + " Does not match supported types");
    }

}
