package ru.alexanderdv.schooltester.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A <code>Transferable</code> which implements the capability required to transfer a <code>String</code>.
 *
 * This <code>Transferable</code> properly supports <code>DataFlavor.stringFlavor</code> and all equivalent flavors. This <code>Transferable</code> properly
 * supports <code>DataFlavor.imageFlavor</code> and all equivalent flavors. This <code>Transferable</code> properly supports
 * <code>DataFlavor.fragmentHtmlFlavor</code> and all equivalent flavors. No other <code>DataFlavor</code>s are supported.
 *
 * @see java.awt.datatransfer.DataFlavor#stringFlavor
 * @see java.awt.datatransfer.DataFlavor#imageFlavor
 * @see java.awt.datatransfer.DataFlavor#fragmentHtmlFlavor
 * @author AlexanderDV/AlexandrDV
 * @version 5.9.0a
 */
public class TableSelection implements Transferable, ClipboardOwner
{

	private static final int STRING = 0;
	private static final int IMAGE = 1;
	private static final int HTML = 2;

	private String data, html;
	private BufferedImage image;
	private int cellW = 40, cellH = 40;

	private static final DataFlavor[] flavors =
	{
			DataFlavor.stringFlavor,
			DataFlavor.imageFlavor,
			DataFlavor.fragmentHtmlFlavor
	};

	/**
	 * Creates a <code>Transferable</code> capable of transferring the specified <code>String</code>.
	 */
	public TableSelection(String data, String ebg, String cbg, String efg, String cfg)
	{
		this.data = data;
		ebg = ebg.substring(ebg.length() - 8, ebg.length() - 2);
		cbg = cbg.substring(cbg.length() - 8, cbg.length() - 2);
		efg = efg.substring(efg.length() - 8, efg.length() - 2);
		cfg = cfg.substring(cfg.length() - 8, cfg.length() - 2);
		if (!ebg.startsWith("#"))
			ebg = "#" + ebg;
		if (!cbg.startsWith("#"))
			cbg = "#" + cbg;
		if (!efg.startsWith("#"))
			efg = "#" + efg;
		if (!cfg.startsWith("#"))
			cfg = "#" + cfg;

		int w = 0, h = 0;
		{
			h = 0;
			for (int a = 0; a < data.split("\n").length; a++)
			{
				int ww = 0;
				for (int b = 0; b < data.split("\n")[a].split("\t").length; b++)
					ww += cellW;
				w = Math.max(w, ww);
				h += cellH;
			}
		}
		String html = "Version:1.0\n";
		html += "		StartHTML:0000000105\n";
		html += "		EndHTML:0000003210\n";
		html += "		StartFragment:0000001740\n";
		html += "		EndFragment:0000003158\n";
		html += "		<html xmlns:v=\"urn:schemas-microsoft-com:vml\"\n";
		html += "		xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n";
		html += "		xmlns:x=\"urn:schemas-microsoft-com:office:excel\"\n";
		html += "		xmlns=\"http://www.w3.org/TR/REC-html40\">\n";
		html += "		<head>\n";
		html += "		<meta http-equiv=Content-Type content=\"text/html; charset=utf-8\">\n";
		html += "		<meta name=ProgId content=Excel.Sheet>\n";

		html += "		<style>\n";
		html += "		<!--table\n";
		html += "			{mso-displayed-decimal-separator:\"\\,\";\n";
		html += "			mso-displayed-thousand-separator:\" \";}\n";
		html += "		@page\n";
		html += "			{margin:.75in .7in .75in .7in;\n";
		html += "			mso-header-margin:.3in;\n";
		html += "			mso-footer-margin:.3in;}\n";
		html += "		tr\n";
		html += "			{mso-height-source:auto;}\n";
		html += "		col\n";
		html += "			{mso-width-source:auto;}\n";
		html += "		br\n";
		html += "			{mso-data-placement:same-cell;}\n";
		html += "		td\n";
		html += "			{padding-top:1px;\n";
		html += "			padding-right:1px;\n";
		html += "			padding-left:1px;\n";
		html += "			mso-ignore:padding;\n";
		html += "			color:black;\n";
		html += "			font-size:11.0pt;\n";
		html += "			font-weight:400;\n";
		html += "			font-style:normal;\n";
		html += "			text-decoration:none;\n";
		html += "			font-family:Calibri, sans-serif;\n";
		html += "			mso-font-charset:204;\n";
		html += "			mso-number-format:General;\n";
		html += "			text-align:general;\n";
		html += "			vertical-align:bottom;\n";
		html += "			border:none;\n";
		html += "			mso-background-source:auto;\n";
		html += "			mso-pattern:auto;\n";
		html += "			mso-protection:locked visible;\n";
		html += "			white-space:nowrap;\n";
		html += "			mso-rotate:0;}\n";

		html += "			.crosswordCell\n";
		html += "			{color: " + cfg + ";\n";
		html += "			background: " + cbg + ";\n";
		html += "			mso-pattern: " + cbg + " none;\n";
		html += "			width: " + cellW + "pt;\n";
		html += "			height: " + cellH + "pt;\n";
		html += "			text-align:center;\n";
		html += "			vertical-align:middle;\n";
		html += "			border-left: .5pt solid windowtext;\n";
		html += "			border-right: .5pt solid windowtext;\n";
		html += "			border-top: .5pt solid windowtext;\n";
		html += "			border-bottom: .5pt solid windowtext}\n";

		html += "			.emptyCell\n";
		html += "			{color: " + efg + ";\n";
		html += "			background: " + ebg + ";\n";
		html += "			mso-pattern: " + ebg + " none;\n";
		html += "			width: " + cellW + "pt;\n";
		html += "			height: " + cellH + "pt;\n";
		html += "			text-align:center;\n";
		html += "			vertical-align:middle;\n";
		html += "			border-left: none;\n";
		html += "			border-right: none;\n";
		html += "			border-top: none;\n";
		html += "			border-bottom: none}\n";

		html += "		-->\n";
		html += "		</style>\n";

		html += "		</head>\n";
		html += "		<body link=blue vlink=purple>\n";
		html += "		<table border=0 cellpadding=0 cellspacing=0 width=192 style='border-collapse:\n";
		html += "		 collapse;width:144pt'>\n";
		// html += " <!--StartFragment-->\n";

		// html += " <col width=64 span=3 style='width:48pt'>\n";

		for (String columns : data.split("\n"))
		{
			html += "		 <tr height=20 style='height:15.0pt'>\n";
			for (int b = 0; b < w / cellW; b++)
			{
				String cell = (b < columns.split("\t").length ? columns.split("\t")[b] : "");
				if (cell.equals(""))
					html += "<td class=emptyCell width=" + cellW + " height=" + cellH + ">" + cell + "</td>\n";
				else html += "<td class=crosswordCell width=" + cellW + " height=" + cellH + ">" + cell + "</td>\n";
			}
			html += "		 </tr>\n";
		}
		// html += " <!--EndFragment-->\n";
		html += "		</table>\n";
		html += "		</body>\n";
		html += "		</html>";
		this.html = html;
		{
			BufferedImage bi = new BufferedImage(w + 1, h + 1, BufferedImage.TYPE_INT_RGB);

			Graphics g = bi.getGraphics();
			// for (int a = 0; a < bi.getWidth(); a++)
			// for (int b = 0; b < bi.getHeight(); b++)
			// bi.setRGB(a, b, 0xFFFFFF);
			g.setColor(Color.white);
			g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
			g.setColor(Color.black);
			for (int a = 0; a < h / cellH; a++)
				for (int b = 0; b < w / cellW; b++)
				{
					String s = b < data.split("\n")[a].split("\t").length ? data.split("\n")[a].split("\t")[b] : "";
					if (s.equals(""))
						g.setColor(Color.decode(ebg));
					else g.setColor(Color.decode(cbg));
					g.fillRect(b * (cellW) + 1, a * (cellH) + 1, cellW, cellH);
					if (!s.equals(""))
					{
						// g.drawRect(b * (cellW - 1), a * (cellH - 1), cellW, cellH);
						g.setColor(Color.black);
						g.drawRect(b * (cellW), a * (cellH), cellW, cellH);
						if (s.equals(""))
							g.setColor(Color.decode(efg));
						else g.setColor(Color.decode(cfg));
						g.drawString(s, b * cellW + cellW / 2 - (int) MathAndTextUtils.size(s, g.getFont()).getWidth() / 2, a * cellH + cellH / 2
								+ (int) MathAndTextUtils.size(s, g.getFont()).getHeight() / 2);
					}
				}
			image = bi;
		}

	}

	/**
	 * Returns an array of flavors in which this <code>Transferable</code> can provide the data. <code>DataFlavor.stringFlavor</code> is properly supported.
	 * Support for <code>DataFlavor.plainTextFlavor</code> is <b>deprecated</b>.
	 *
	 * @return an array of length two, whose elements are <code>DataFlavor.
	 *         stringFlavor</code> and <code>DataFlavor.plainTextFlavor</code>
	 */
	public DataFlavor[] getTransferDataFlavors()
	{
		// returning flavors itself would allow client code to modify
		// our internal behavior
		return (DataFlavor[]) flavors.clone();
	}

	/**
	 * Returns whether the requested flavor is supported by this <code>Transferable</code>.
	 *
	 * @param flavor
	 *            the requested flavor for the data
	 * @return true if <code>flavor</code> is equal to <code>DataFlavor.stringFlavor</code> or <code>DataFlavor.plainTextFlavor</code>; false if
	 *         <code>flavor</code> is not one of the above flavors
	 * @throws NullPointerException
	 *             if flavor is <code>null</code>
	 */
	public boolean isDataFlavorSupported(DataFlavor flavor)
	{
		// JCK Test StringSelection0003: if 'flavor' is null, throw NPE
		for (int i = 0; i < flavors.length; i++)
		{
			if (flavor.equals(flavors[i]))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the <code>Transferable</code>'s data in the requested <code>DataFlavor</code> if possible. If the desired flavor is
	 * <code>DataFlavor.stringFlavor</code>, or an equivalent flavor, the <code>String</code> representing the selection is returned. If the desired flavor is
	 * <code>DataFlavor.plainTextFlavor</code>, or an equivalent flavor, a <code>Reader</code> is returned. <b>Note:</b> The behavior of this method for
	 * <code>DataFlavor.plainTextFlavor</code> and equivalent <code>DataFlavor</code>s is inconsistent with the definition of
	 * <code>DataFlavor.plainTextFlavor</code>.
	 *
	 * @param flavor
	 *            the requested flavor for the data
	 * @return the data in the requested flavor, as outlined above
	 * @throws UnsupportedFlavorException
	 *             if the requested data flavor is not equivalent to either <code>DataFlavor.stringFlavor</code> or <code>DataFlavor.plainTextFlavor</code>
	 * @throws IOException
	 *             if an IOException occurs while retrieving the data. By default, StringSelection never throws this exception, but a subclass may.
	 * @throws NullPointerException
	 *             if flavor is <code>null</code>
	 * @see java.io.Reader
	 */
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException
	{
		// JCK Test StringSelection0007: if 'flavor' is null, throw NPE
		if (flavor.equals(flavors[STRING]))
		{
			return (Object) data;
		}
		else if (flavor.equals(flavors[HTML]))
		{
			return (Object) html;
		}
		else if (flavor.equals(flavors[IMAGE]))
		{
			return image;
		}
		else
		{
			throw new UnsupportedFlavorException(flavor);
		}
	}

	public void lostOwnership(Clipboard clipboard, Transferable contents)
	{
	}
}
