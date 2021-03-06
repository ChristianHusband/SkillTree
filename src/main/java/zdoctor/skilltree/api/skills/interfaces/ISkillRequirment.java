package zdoctor.skilltree.api.skills.interfaces;

import java.util.function.Predicate;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;

public interface ISkillRequirment extends Predicate<EntityLivingBase> {
	/**
	 * What happens when the player pays the requirements
	 * 
	 * @param entity
	 *            The entity
	 * 
	 */
	public void onFufillment(EntityLivingBase entity);

	/**
	 * What shows up in the tooltip, will be formatted
	 * 
	 * @param entity
	 *            The entity
	 * 
	 */
	public String getDescription();

	/**
	 * This will be passed as the objets to {@link I18n} when the description is
	 * called
	 * 
	 * @param entity
	 *            The entity
	 * @return
	 */
	public default Object[] getDescriptionValues() {
		return new Object[0];
	}

	/**
	 * Gets the text color based on if the player has the skill or not.
	 * 
	 * @param entity
	 *            The entity
	 */
	public default int getTextColor(EntityLivingBase entity) {
		return !test(entity) ? 0xFF0000 : 0x00FF00;
	}

}
