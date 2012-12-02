package net.mklew.hotelms.domain.guests;


import org.joda.time.DateTime;

/**
 * @author: Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since: 9/9/12
 * time 9:39 PM
 */
public class Guest extends Person
{
    private String socialTitle;
    private String firstName;
    private String middleName;
    private String surname;
    private Gender gender;
    private DocumentType documentType;
    private String documentId;
    private String nationality;
    private DateTime dateOfBirth;
    private String phoneNumber;
    private String faxNumber;
    private String emailAddress;
    private String preferences;
    private WorkDetails workDetails;
    private String website;

    Guest()
    {

    }

    public Guest(String socialTitle, String firstName, String surname, Gender gender, DocumentType documentType,
                 String documentId, String phoneNumber)
    {
        this.socialTitle = socialTitle;
        this.firstName = firstName;
        this.surname = surname;
        this.gender = gender;
        this.documentType = documentType;
        this.documentId = documentId;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns WorkInformation
     *
     * @return work information, address and work place details.
     * @throws GuestHasNoWorkInformationException when work information is not found
     */
    public WorkInformation getWorkInformation()   throws GuestHasNoWorkInformationException
    {
        for (Address address : getAddresses())
        {
            if (address.getAddressType().equals(AddressType.WORK_ADDRESS))
            {
                return new WorkInformation(workDetails, address);
            }
        }
        throw new GuestHasNoWorkInformationException();
    }

    public void addWorkInformation(WorkInformation workInformation)
    {
        getAddresses().add(workInformation.getAddress());
        this.workDetails = workInformation.getWorkDetails();
    }


    public String getSocialTitle()
    {
        return socialTitle;
    }

    public void setSocialTitle(String socialTitle)
    {
        this.socialTitle = socialTitle;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public DocumentType getDocumentType()
    {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType)
    {
        this.documentType = documentType;
    }

    public String getDocumentId()
    {
        return documentId;
    }

    public void setDocumentId(String documentId)
    {
        this.documentId = documentId;
    }

    public String getNationality()
    {
        return nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    public DateTime getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateTime dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber()
    {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber)
    {
        this.faxNumber = faxNumber;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getPreferences()
    {
        return preferences;
    }

    public void setPreferences(String preferences)
    {
        this.preferences = preferences;
    }

    public WorkDetails getWorkDetails()
    {
        return workDetails;
    }

    public void setWorkDetails(WorkDetails workDetails)
    {
        this.workDetails = workDetails;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }
}
