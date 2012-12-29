package net.mklew.hotelms.inhouse.web.dto;

import net.mklew.hotelms.domain.guests.DocumentType;
import net.mklew.hotelms.domain.guests.Gender;
import net.mklew.hotelms.domain.guests.Guest;
import net.mklew.hotelms.inhouse.web.dto.dates.DateParser;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;

import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 11/30/12
 *        time 8:14 PM
 */
@XmlRootElement(name = "guestdto")
public class GuestDto
{
    public String id;
    public String firstName;
    public String middleName;
    public String surname;
    public String displayed;
    public String socialTitle;
    public String sex;
    public String phoneNumber;
    public String nationality;
    public String idNumber;
    public String dateOfBirth;
    public String preferences;
    public String documentType; // TODO need to change naming idType and documentType are meant to be the same,
    // it should be something like 'documentType' and 'documentTypeEnum' or something


    @JsonIgnore
    public transient DocumentType idType;
    @JsonIgnore
    public transient DateTime dateOfBirthDate;
    @JsonIgnore
    public transient Gender gender;


    public GuestDto()
    {
    }

    public static Collection<GuestDto> fromGuests(Collection<Guest> guests)
    {
        Collection<GuestDto> dtos = new ArrayList<>(guests.size());
        for (Guest guest : guests)
        {
            dtos.add(fromGuest(guest));
        }
        return dtos;
    }

    public boolean exists()
    {
        return !id.equals("");
    }

    public static GuestDto fromReservationForm(MultivaluedMap<String, String> formData) throws MissingGuestInformation
    {
        validateRequiredInformation(formData);
        GuestDto dto = new GuestDto();
        // populate required properties
        dto.id = formData.getFirst("ownerId") != null ? formData.getFirst("ownerId") : "";
        dto.socialTitle = formData.getFirst("socialTitle");
        dto.firstName = formData.getFirst("firstName");
        //dto.middleName;
        dto.surname = formData.getFirst("surname");
        dto.sex = formData.getFirst("sex");
        dto.gender = Gender.fromName(formData.getFirst("sex"));
        dto.phoneNumber = formData.getFirst("phoneNumber");
        dto.nationality = formData.getFirst("nationality");
        dto.idType = DocumentType.fromString(formData.getFirst("idType"));
        dto.idNumber = formData.getFirst("idNumber");

        // populate optional properties
        String dateOfBirthString = formData.getFirst("dateOfBirth");
        if (dateOfBirthString != null)
        {
            dto.dateOfBirth = dateOfBirthString;
            dto.dateOfBirthDate = DateParser.fromString(dateOfBirthString);
        }
        else
        {
            dto.dateOfBirth = "";
        }
        dto.preferences = formData.getFirst("preferences") != null ? formData.getFirst("preferences") : "";

        // TODO create home address from data provided
        // TODO AddressDTO

        return dto;
    }


    private static void validateRequiredInformation(MultivaluedMap<String,
            String> formData) throws MissingGuestInformation
    {
        if (!(formData.getFirst("socialTitle") != null))
        {
            throw new MissingGuestInformation("Missing socialTitle");
        }
        if (!(formData.getFirst("firstName") != null))
        {
            throw new MissingGuestInformation("Missing first name");
        }
        if (!(formData.getFirst("surname") != null))
        {
            throw new MissingGuestInformation("Missing surname");
        }
        if (!(formData.getFirst("sex") != null))
        {
            throw new MissingGuestInformation("Missing sex");
        }
        if (!(formData.getFirst("phoneNumber") != null))
        {
            throw new MissingGuestInformation("Missing phone number");
        }
        if (!(formData.getFirst("nationality") != null))
        {
            throw new MissingGuestInformation("Missing nationality");
        }
        if (!(formData.getFirst("idType") != null))
        {
            throw new MissingGuestInformation("Missing id type");
        }
        if (!(formData.getFirst("idNumber") != null))
        {
            throw new MissingGuestInformation("Missing id number");
        }
    }

    public static GuestDto fromGuest(Guest guest)
    {
        GuestDto dto = new GuestDto();
        dto.firstName = guest.getFirstName();
        dto.middleName = guest.getMiddleName() != null ? guest.getMiddleName() : "";
        dto.surname = guest.getSurname();
        dto.displayed = guest.getFirstName() + " " + guest.getSurname();
        dto.id = String.valueOf(guest.getId());
        dto.socialTitle = guest.getSocialTitle();
        dto.sex = guest.getGender().getName();
        dto.phoneNumber = guest.getPhoneNumber();
        dto.nationality = guest.getNationality();
        dto.documentType = guest.getDocumentType().getName();
        dto.idType = guest.getDocumentType(); // this is not needed, just wanna check how it looks in json
        dto.gender = guest.getGender(); // same as above
        dto.idNumber = guest.getDocumentId();
        dto.dateOfBirth = guest.getDateOfBirth() != null ? DateParser.fromDate(guest.getDateOfBirth()) : "";
        dto.preferences = guest.getPreferences();
        return dto;
    }

    public static GuestDto fromNewGuestForm(MultivaluedMap<String, String> formParams) throws MissingGuestInformation
    {
        // TODO sort these method names out, because probably both will have same data
        return fromReservationForm(formParams);
    }
}
