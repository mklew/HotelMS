package net.mklew.hotelms.inhouse.web.modules.components.test;

import org.objectledge.context.Context;
import org.objectledge.i18n.I18nContext;
import org.objectledge.modules.components.BaseLedgeComponent;
import org.objectledge.parameters.Parameters;
import org.objectledge.pipeline.ProcessingException;
import org.objectledge.templating.TemplatingContext;
import org.objectledge.web.HttpContext;
import org.objectledge.web.mvc.MVCContext;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 10/23/12
 *        Time: 2:44 PM
 */
public class TestDynamicComponent extends BaseLedgeComponent
{
    public TestDynamicComponent(Context context)
    {
        super(context);
    }

    @Override
    public void process(Parameters parameters, TemplatingContext templatingContext, MVCContext mvcContext, HttpContext httpContext, I18nContext i18nContext) throws ProcessingException
    {

    }
}
