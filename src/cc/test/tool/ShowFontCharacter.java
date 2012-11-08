package cc.test.tool;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import cc.adjusting.bolder.NullStroke;
import cc.example.awt.AwtTestExample;

public class ShowFontCharacter extends AwtTestExample
{
	/** 序列化編號 */
	private static final long serialVersionUID = 1L;
	/** 字型大小 */
	static final int TYPE_SIZE = 40;

	/**
	 * @param args
	 */
	@Override
	public void paint(Graphics g1)
	{
		// TODO Auto-generated method stub
		try
		{
			Graphics2D graphics2D = (Graphics2D) g1;
			Font fontㄉ = new Font("Sans", 0, 200);
			GlyphVector glyphv = fontㄉ.createGlyphVector(new FontRenderContext(
					new AffineTransform(),
					java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT,
					java.awt.RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT),
					"HI");
			System.out.println("gl=" + glyphv.getNumGlyphs());
			graphics2D.translate(getWidth() - TYPE_SIZE * 1.1, TYPE_SIZE);
			// graphics2D.translate(300, 300);
			graphics2D.setStroke(new NullStroke());
			// graphics2D.draw(glyphv.getOutline());

			Font font = Font.createFont(Font.TRUETYPE_FONT, new File(
					"/home/ihc/桌面/cdpeudck.ttf"));
			font = font.deriveFont(TYPE_SIZE * 1.0f);
			// font=fontㄉ;
			int cnt = 0;
			int which = 0;
			for (int i = 0; i < 0xFFFFF; ++i)
			{
				if (font.canDisplay(i))
				{
					// if(which==0)
					which = i;
					char[] chars = Character.toChars(which);
					GlyphVector gv = font
							.createGlyphVector(
									new FontRenderContext(
											new AffineTransform(),
											java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT,
											java.awt.RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT),
									chars);
					// System.out.println(gv.getOutline().getBounds2D().getWidth());
					if (gv.getOutline().getBounds2D().getWidth() > 0.1)
					{
						// System.out.println("gv="+gv.getNumGlyphs());
						++cnt;
						final int pro = 1, line = 25;
						if (cnt % pro == 0)
						{
							graphics2D.draw(gv.getOutline());
							graphics2D.translate(0, TYPE_SIZE);
							if (cnt % (line * pro) == 0)
								graphics2D.translate(-TYPE_SIZE, TYPE_SIZE
										* -line);
							System.out.println("i=" + i);
						}
					}
				}
			}
			System.out.println("count=" + cnt);
		} catch (FontFormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 主函式，設定相關視窗資訊。
	 * 
	 * @param args
	 *            呼叫引數
	 */
	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		f.setContentPane(new ShowFontCharacter());
		f.setSize(WIDTH, HEIGHT);
		f.setVisible(true);
	}
}
