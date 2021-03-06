package berlin.tablecodes.assets;

import berlin.tablecodes.conditions.ConditionRating;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Required;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Key")
@CompanionObject(IAssetType.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@DescRequired
// TODO: May need this later if some entities need to be automatically cascade-deactivated when this entity is deactivated
// @DeactivatableDependencies({ Dependency1.class, Dependency2.class, Dependency3.class })
public class AssetType extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetType.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();
    
    @IsProperty
    @MapTo
    @Title(value = "Name", desc = "A unique value for this asset type.")
    @CompositeKeyMember(1)
    private String name;
    
    @IsProperty
    @MapTo
    @Title(value = "Condition Rating", desc = "Condition rating for assets of this type")
    private ConditionRating conditionRating;

    @Observable
    public AssetType setConditionRating(final ConditionRating conditionRating) {
        this.conditionRating = conditionRating;
        return this;
    }

    public ConditionRating getConditionRating() {
        return conditionRating;
    }
    
    @IsProperty
    @MapTo
    @Required
    @Title(value = "Asset Class", desc = "The class of this asset type")
    private AssetClass assetClass;

    @Observable
    public AssetType setAssetClass(final AssetClass assetClass) {
        this.assetClass = assetClass;
        return this;
    }

    public AssetClass getAssetClass() {
        return assetClass;
    }

    @Observable
    public AssetType setName(final String name) {
        this.name = name;
        return this;
    }
    
    @Override
    @Observable
    public AssetType setDesc(String desc) {
        super.setDesc(desc);
        return this;
    }

    public String getName() {
        return name;
    }
    
    @Override
    @Observable
    public AssetType setActive(boolean active) {
        super.setActive(active);
        return this;
    }

}
