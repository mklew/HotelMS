package net.mklew.hotelms.inhouse.web.modules.views.top;

import org.objectledge.context.Context;
import org.objectledge.i18n.I18nContext;
import org.objectledge.modules.views.BasicLedgeView;
import org.objectledge.parameters.Parameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.HttpContext;
import org.objectledge.web.mvc.MVCContext;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 10/25/12
 *        Time: 8:30 PM
 */
public class ReservationTable extends BasicLedgeView
{
    /**
     * Constructs a builder instance.
     *
     * @param context application context for use by this builder.
     */
    public ReservationTable(Context context)
    {
        super(context);
    }

    @Override
    public void process(Parameters parameters, TemplatingContext templatingContext, MVCContext mvcContext, HttpContext httpContext, I18nContext i18nContext)
            throws ProcessingException
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}
