package berlin.tablecodes.assets;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import berlin.assets.Asset;
import berlin.tablecodes.capex.Capex;
import berlin.test_config.AbstractDaoTestCase;
import berlin.test_config.UniversalConstantsForTesting;
import ua.com.fielden.platform.utils.IUniversalConstants;

public class CapexTest extends AbstractDaoTestCase {

    @Test
    public void property_title_is_determined_correctly_even_without_title_annotation() {
        assertEquals(Capex.ENTITY_TITLE, co$(Asset.class).new_().getProperty("capex").getTitle());
    }
    
    @Override
    public boolean saveDataPopulationScriptToFile() {
        return false;
    }

    @Override
    public boolean useSavedDataPopulationScript() {
        return false;
    }

    @Override
    protected void populateDomain() {
        super.populateDomain();

        final UniversalConstantsForTesting constants = (UniversalConstantsForTesting) getInstance(IUniversalConstants.class);
        constants.setNow(dateTime("2019-10-01 11:30:00"));

        if (useSavedDataPopulationScript()) {
            return;
        }
    }
}
