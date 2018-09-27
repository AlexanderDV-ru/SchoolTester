package ru.alexanderdv.schooltester.utilities;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * A <code>Transferable</code> which implements the capability required to
 * transfer a <code>Image</code>.
 *
 * This <code>Transferable</code> properly supports
 * <code>DataFlavor.imageFlavor</code> and all equivalent flavors. No other
 * <code>DataFlavor</code>s are supported.
 *
 * @see java.awt.datatransfer.DataFlavor#imageFlavor
 * @author AlexanderDV
 * @version 6.1.5a
 */
public class ImageSelection implements Transferable, ClipboardOwner
{
	private java.awt.Image image;

	/**
	 * Creates a <code>Transferable</code> capable of transferring the specified
	 * <code>Image</code>.
	 */
	public ImageSelection(java.awt.Image image)
	{
		this.image = image;
	}

	/**
	 * Returns an array of flavors in which this <code>Transferable</code> can
	 * provide the data. <code>DataFlavor.imageFlavor</code> is properly supported.
	 *
	 * @return an array of length two, whose elements are <code>DataFlavor.
	 *         imageFlavor</code>
	 */
	public DataFlavor[] getTransferDataFlavors()
	{
		return new DataFlavor[] { DataFlavor.imageFlavor };
	}

	/**
	 * Returns whether the requested flavor is supported by this
	 * <code>Transferable</code>.
	 *
	 * @param flavor
	 *            the requested flavor for the data
	 * @return true if <code>flavor</code> is equal to
	 *         <code>DataFlavor.imageFlavor</code>; false if <code>flavor</code> is
	 *         not one of the above flavors
	 * @throws NullPointerException
	 *             if flavor is <code>null</code>
	 */
	public boolean isDataFlavorSupported(DataFlavor flavor)
	{
		return DataFlavor.imageFlavor.equals(flavor);
	}

	/**
	 * Returns the <code>Transferable</code>'s data in the requested
	 * <code>DataFlavor</code> if possible. If the desired flavor is
	 * <code>DataFlavor.imageFlavor</code>, or an equivalent flavor, the
	 * <code>Image</code> representing the selection is returned.
	 *
	 * @param flavor
	 *            the requested flavor for the data
	 * @return the data in the requested flavor, as outlined above
	 * @throws UnsupportedFlavorException
	 *             if the requested data flavor is not equivalent to either
	 *             <code>DataFlavor.imageFlavor</code> or
	 * @throws IOException
	 *             if an IOException occurs while retrieving the data. By default,
	 *             ImageSelection never throws this exception, but a subclass may.
	 * @throws NullPointerException
	 *             if flavor is <code>null</code>
	 * @see java.io.Reader
	 */
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
	{
		if (!DataFlavor.imageFlavor.equals(flavor))
			throw new UnsupportedFlavorException(flavor);
		return image;
	}

	public void lostOwnership(Clipboard clipboard, Transferable contents)
	{
	}
}