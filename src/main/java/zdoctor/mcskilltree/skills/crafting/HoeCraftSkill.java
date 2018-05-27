package zdoctor.mcskilltree.skills.crafting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import zdoctor.mcskilltree.event.CraftingEvent;
import zdoctor.skilltree.api.SkillTreeApi;
import zdoctor.skilltree.api.enums.SkillFrameType;
import zdoctor.skilltree.api.skills.interfaces.ISkillRequirment;
import zdoctor.skilltree.api.skills.interfaces.ISkillStackable;
import zdoctor.skilltree.api.skills.requirements.LevelRequirement;
import zdoctor.skilltree.api.skills.requirements.SkillPointRequirement;

public class HoeCraftSkill extends ItemCrafterSkill implements ISkillStackable {
	public HoeCraftSkill() {
		super(ItemHoe.class, "HoeCrafter", Items.DIAMOND_HOE);
	}

	@Override
	public void onSkillRePurchase(EntityLivingBase entity) {
		// TODO Toast
	}

	@Override
	public List<ISkillRequirment> getRequirments(EntityLivingBase entity, boolean hasSkill) {
		List<ISkillRequirment> list = new ArrayList<>();
		int tier = SkillTreeApi.getSkillTier(entity, this);
		if (tier >= getMaxTier(entity))
			return list;
		list.add(new LevelRequirement(3 * (tier + 1)));
		list.add(new SkillPointRequirement(tier + 1));
		return list;
	}
	
	@Override
	public SkillFrameType getFrameType(EntityLivingBase entity) {
		return SkillTreeApi.getSkillTier(entity, this) >= 5 ? SkillFrameType.SPECIAL
				: SkillTreeApi.getSkillTier(entity, this) == 4 ? SkillFrameType.ROUNDED : SkillFrameType.NORMAL;
	}

	@Override
	public ItemStack getIcon(EntityLivingBase entity) {
		int tier = SkillTreeApi.getSkillTier(entity, this);
		switch (tier) {
		case 0:
			return new ItemStack(Items.WOODEN_HOE);
		case 1:
			return new ItemStack(Items.WOODEN_HOE);
		case 2:
			return new ItemStack(Items.STONE_HOE);
		case 3:
			return new ItemStack(Items.GOLDEN_HOE);
		case 4:
			return new ItemStack(Items.IRON_HOE);
		case 5:
			return new ItemStack(Items.DIAMOND_HOE);
		default:
			return super.getIcon(entity);
		}
	}

	@Override
	public int getMaxTier(EntityLivingBase entity) {
		return ToolMaterial.values().length;
	}

	@Override
	public void craftEvent(CraftingEvent event) {
		super.craftEvent(event);
		if (getItemClass().isAssignableFrom(event.getRecipeResult().getItem().getClass())) {
			if (event.getResult() != Result.DENY) {
				ItemHoe hoe = (ItemHoe) event.getRecipeResult().getItem();
				int tier = SkillTreeApi.getSkillTier(event.getPlayer(), this);
				ToolMaterial material = ToolMaterial.valueOf(hoe.getMaterialName());
				int craftDificulty;
				switch (material) {
				case WOOD:
					craftDificulty = 1;
					break;
				case STONE:
					craftDificulty = 2;
					break;
				case GOLD:
					craftDificulty = 3;
					break;
				case IRON:
					craftDificulty = 4;
					break;
				case DIAMOND:
					craftDificulty = 5;
					break;
				default:
					craftDificulty = material.getHarvestLevel();
					break;
				}

				if (craftDificulty > tier)
					event.setResult(Result.DENY);
			}
		}
	}
}