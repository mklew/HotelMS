package net.mklew.hotelms.domain.guests;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12
 *        time 8:44 PM
 */
public enum DocumentType
{
    DRIVER_LICENSE
            {
                @Override
                public String getName()
                {
                    return "driverLicense";
                }
            }, PASSPORT_ID
        {
            @Override
            public String getName()
            {
                return "passport";
            }
        }, PERSONAL_ID
        {
            @Override
            public String getName()
            {
                return "personalID";
            }
        }, OTHER
        {
            @Override
            public String getName()
            {
                return "other";
            }
        };

    public abstract String getName();

    public static DocumentType fromString(String documentType)
    {
        for (DocumentType type : DocumentType.values())
        {
            if (type.getName().equals(documentType))
            {
                return type;
            }
        }
        throw new RuntimeException("DocumentType from string: " + documentType + " cannot be resolved." +
                " Does not match supported types");
    }
}
