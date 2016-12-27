package teamkakkokari.travellers.src.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import teamkakkokari.travellers.src.background.Background;
import teamkakkokari.travellers.src.structure.Structure;

/**
 * Travellers内で使用するリソースをまとめたマネージャーです。<br>
 * ゲーム起動時にResourceManagerインスタンスを生成し、それを各Guiなどのインスタンス生成時に引数として渡すことでプログラムの高速化を図ります。
 *
 * @author TEAMKakkokari, T-I
 *
 */
public class ResourceManager {

	/**
	 * ResourceManagerインスタンスを生成します。
	 */
	public ResourceManager() {
		_textureBackground = new BufferedImage[Background.backgrounds.length];
		Background background;
		for (int i = 0; i < Background.backgrounds.length; i++) {
			background = Background.getBackground(i);
			_textureBackground[i] = read(background.getTexture());
		}
		_textureStructure = new BufferedImage[Structure.structures.length];
		Structure structure;
		for (int i = 0; i < Structure.structures.length; i++) {
			structure = Structure.getStructure(i);
			_textureStructure[i] = read(structure.getTexture());
		}
		_logo = read(LOGO);
		_texturePlayer = read(TEXTURE_PLAYER);
		_textureEnemy = read(TEXTURE_ENEMY);
		_textureItem = read(TEXTURE_ITEM);
		_random = new Random();
	}

	/**
	 * 乱数生成用変数
	 */
	public final Random _random;
	/**
	 * ロゴ・イメージ
	 */
	public final BufferedImage _logo;
	/**
	 * バックグラウンド・テクスチャイメージ。配列は以下の通り。
	 * 0 - Grass.png草原
	 * 1 - Tatami1.png畳（右向き）
	 * 2 - Tatami2.png畳（下向き）
	 * 3 - Tatami3.png畳（左向き）
	 * 4 - Tatami4.png畳（上向き）
	 * 5 - Tatami5.png畳（半畳）
	 * 6 - Tatami6.png畳（半畳、網目縦向き）
	 */
	public final BufferedImage[] _textureBackground;
	/**
	 * ストラクチャ・テクスチャイメージ。配列は以下の通り。
	 * 0 - テクスチャ表示なし（null）
	 * 1 - テクスチャ無し壁（null）
	 * 2 - Box1.png箱1
	 * 3 - Chest1.png木箱1
	 * 4 - Chest2.png木箱2
	 * 5 - Door1.png扉1
	 * 6 - Key.png鍵
	 */
	public final BufferedImage[] _textureStructure;
	/**
	 * プレイヤー・テクスチャイメージ。配列は以下の通り。
	 * 0 - プレイヤー（上向き）
	 * 1 - プレイヤー（下向き）
	 * 2 - プレイヤー（左向き）
	 * 3 - プレイヤー（右向き）
	 */
	public final BufferedImage[] _texturePlayer;
	/**
	 * エネミー・テクスチャイメージ。配列は以下の通り。
	 * 0 - Mimic.pngミミック
	 * 1 - Slime1.pngスライム1
	 * 2 - Ameba1.pngアメーバ1
	 */
	public final BufferedImage[] _textureEnemy;
	/**
	 * アイテム・テクスチャイメージ。配列は以下の通り。
	 */
	public final BufferedImage[] _textureItem;

	private static final String LOGO = "Logo.png";
	private static final String[] TEXTURE_PLAYER = {
			"HeroUp.png",
			"HeroDown.png",
			"HeroLeft.png",
			"HeroRight.png",
	};
	private static final String[] TEXTURE_ENEMY = {
			"Mimic.png",
			"Slime1.png",
			"Ameba1.png",
	};
	private static final String[] TEXTURE_ITEM = {

	};

	private BufferedImage read(String input) {
		if (input == null) {
			return null;
		}
		try {
			return ImageIO.read(new File("TEAMKakkokari\\Travellers1\\" + input));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private BufferedImage[] read(String[] input) {
		BufferedImage[] texture = new BufferedImage[input.length];
		try {
			for (int i = 0; i < input.length; i++) {
				if (input[i] == null) {
					texture[i] = null;
				} else {
					texture[i] = ImageIO.read(new File("TEAMKakkokari\\Travellers1\\" + input[i]));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return texture;
	}

}
