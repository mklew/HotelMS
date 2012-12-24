package net.mklew.hotelms.domain.guests;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12
 *        time 8:44 PM
 */
public enum DocumentType
{
    DRIVER_LICENSE, PASSPORT_ID, PERSONAL_ID, OTHER;

    public static DocumentType fromString(String documentType)
    {
        if ("passport".equals(documentType))
        {
            return PASSPORT_ID;
        } else if ("personalID".equals(documentType))
        {
            return PERSONAL_ID;
        } else if ("driverLicense".equals(documentType))
        {
            return DRIVER_LICENSE;
        } else if ("other".equals(documentType))
        {
            return OTHER;
        } else
        {
            throw new RuntimeException("DocumentType from string: " + documentType + " cannot be resolved." +
                    " Does not match supported types");
        }
    }
}
