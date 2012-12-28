package net.mklew.hotelms.inhouse.web.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/28/12
 *        time 5:32 PM
 */
@XmlRootElement(name = "errordto")
public class ErrorDto
{
    private String message;
    private String errorCode;

    public ErrorDto(String message)
    {
        this.message = message;
    }

    public ErrorDto(String message, String errorCode)
    {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }
}
