package zdoctor.skilltree.api.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.Event.HasResult;
import net.minecraftforge.fml.relauncher.Side;
import zdoctor.skilltree.api.enums.EnumSkillInteractType;
import zdoctor.skilltree.api.skills.interfaces.ISkill;

/**
 * Called when a player clicks a skill. Will auto sync by default unless result
 * in denied or canceled
 */
@Cancelable
@HasResult
public class SkillInteractEvent extends Event {

	public final EntityPlayer player;
	public final ISkill skill;
	public final EnumSkillInteractType type;
	public final Side side;

	public SkillInteractEvent(EntityPlayer player, ISkill skill, EnumSkillInteractType type, Side side) {
		this.player = player;
		this.skill = skill;
		this.type = type;
		this.side = side;
	}
}
