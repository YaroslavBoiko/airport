package berlin.assets.validators;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.Set;

import berlin.assets.AssetFinDet;
import berlin.tablecodes.projects.Project;
import ua.com.fielden.platform.entity.meta.MetaProperty;
import ua.com.fielden.platform.entity.meta.impl.AbstractBeforeChangeEventHandler;
import ua.com.fielden.platform.entity.query.model.EntityResultQueryModel;
import ua.com.fielden.platform.error.Result;

import static ua.com.fielden.platform.error.Result.failure;
import static ua.com.fielden.platform.error.Result.successful;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.select;

public class AssetFinDetAcquireDateWithinProjectPeriodValidator extends AbstractBeforeChangeEventHandler<Date> {
    public static final String ERR_OUTSIDE_PROJECT_PERIOD = "Value for acquire date is outside the project period.";

    @Override
    public Result handle(final MetaProperty<Date> property, final Date newValue, final Set<Annotation> mutatorAnnotations) {
        final AssetFinDet finDet = property.getEntity();
        if (finDet.getProject() == null || newValue == null) {
            return successful(newValue);
        }
        
        final EntityResultQueryModel<Project> query = select(Project.class)
                .where().prop("id").eq().val(finDet.getProject()).and()
                .prop("startDate").le().val(newValue).and()
                .begin()
                    .prop("finishDate").isNull().or()
                    .prop("finishDate").ge().val(newValue)
                .end().model();
        return co(Project.class).exists(query) ? successful(newValue) : failure(ERR_OUTSIDE_PROJECT_PERIOD);
    }

}
