package me.Feazes.plugins.mobcash;

import org.bukkit.entity.Entity;

public class EntityName {
	
	public static String readEntity(Entity et) {
		switch (et.getType()) {
		//mobs before 1.8
		case BAT:return"Bat";
		case BLAZE:return"Blaze";
		case CAVE_SPIDER:return"Cave Spider";
		case CHICKEN:return"Chicken";
		case COW:return"Cow";
		case CREEPER:return"Creeper";
		case ENDERMAN:return"Enderman";
		case ENDER_DRAGON:return"Ender Dragon";
		case GHAST:return"Ghast";
		case GIANT:return"Giant";
		case HORSE:return"Horse";
		case IRON_GOLEM:return"Iron Golem";
		case MAGMA_CUBE:return"Magma Cube";
		case MUSHROOM_COW:return"Mushroom Cow";
		case OCELOT:return"Ocelot";
		case PIG:return"Pig";
		case PIG_ZOMBIE:return"Pig Zombie";
		case SHEEP:return"Sheep";
		case SILVERFISH:return"Silver Fish";
		case SKELETON:return"Skeleton";
		case SLIME:return"Slime";
		case SNOWMAN:return"Snowman";
		case SPIDER:return"Spider";
		case SQUID:return"Squid";
		case VILLAGER:return"Villager";
		case WITCH:return"Witch";
		case WITHER:return"Wither";
		case WITHER_SKELETON:return"Wither Skeleton";
		case WOLF:return"Wolf";
		case ZOMBIE:return"Zombie";
		//1.8 mobs 
		case ENDERMITE:return"Endermite";
		case GUARDIAN:return"Guardian";
		case RABBIT:return"Rabbit";
		//1.9 mobs
		case SHULKER:return"Shulker";
		//1.10 mobs
		case POLAR_BEAR:return"Polar Bear";
		//1.11 mobs
		case ZOMBIE_VILLAGER:return"Zombie Villager";
		case ZOMBIE_HORSE:return"Zombie Horse";
		case ELDER_GUARDIAN: return "Elder Guardian";
		case EVOKER:return"Evoker";
		case VEX:return"Vex";
		case VINDICATOR:return"Vindicator";
		case SKELETON_HORSE:return"Skeleton horse";
		case LLAMA:return"Llama";
		case STRAY:return"Stray";
		case MULE:return"Mule";
		case HUSK:return"Husk";
		case DONKEY:return"Donkey";
		//1.12 mobs
		case PARROT:return "Parrot";
		case ILLUSIONER:return "Illusioner"; 
		
		default:
			break;
		}
		return null;
	}
}
