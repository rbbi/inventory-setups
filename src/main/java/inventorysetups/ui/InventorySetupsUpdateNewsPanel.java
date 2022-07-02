package inventorysetups.ui;

import com.sun.jna.platform.win32.WinNT;
import inventorysetups.InventorySetupsPlugin;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.util.LinkBrowser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static inventorysetups.InventorySetupsPlugin.SUGGESTION_LINK;
import static inventorysetups.InventorySetupsPlugin.TUTORIAL_LINK;

public class InventorySetupsUpdateNewsPanel extends JPanel
{

	InventorySetupsUpdateNewsPanel(InventorySetupsPlugin plugin, InventorySetupsPluginPanel panel)
	{
		final JLabel welcomeText = new JLabel("Inventory Setups " + plugin.getCurrentVersionString());
		welcomeText.setFont(FontManager.getRunescapeBoldFont());
		welcomeText.setHorizontalAlignment(JLabel.CENTER);

		final JPanel welcomePanel = new JPanel(new BorderLayout());
		welcomePanel.add(welcomeText, BorderLayout.NORTH);

		final JPanel latestUpdatePanelInfo = getLatestUpdateInfoPanel();

		final JLabel newUser = new JLabel("Are you a new user?");
		final JLabel newUser2 = new JLabel("For help and support, click here");
		final JButton linkToHelp = new JButton("View Guide");
		linkToHelp.addActionListener(e ->
		{
			LinkBrowser.browse(TUTORIAL_LINK);
		});
		newUser.setFont(FontManager.getRunescapeSmallFont());
		newUser2.setFont(FontManager.getRunescapeSmallFont());
		newUser.setHorizontalAlignment(JLabel.CENTER);
		newUser2.setHorizontalAlignment(JLabel.CENTER);

		final JPanel newUserPanelInfo = new JPanel();
		newUserPanelInfo.setLayout(new BorderLayout());
		newUserPanelInfo.add(newUser, BorderLayout.NORTH);
		newUserPanelInfo.add(newUser2, BorderLayout.CENTER);
		newUserPanelInfo.add(linkToHelp, BorderLayout.SOUTH);

		final JLabel suggestions = new JLabel("Have a suggestion? Found a bug?");
		final JLabel suggestions2 = new JLabel("Click here to create an issue");
		final JButton linkToSuggestion = new JButton("Make a Suggestion");
		linkToSuggestion.addActionListener(e ->
		{
			LinkBrowser.browse(SUGGESTION_LINK);
		});
		suggestions.setFont(FontManager.getRunescapeSmallFont());
		suggestions2.setFont(FontManager.getRunescapeSmallFont());
		suggestions.setHorizontalAlignment(JLabel.CENTER);
		suggestions2.setHorizontalAlignment(JLabel.CENTER);

		final JPanel suggestionPanelInfo = new JPanel();
		suggestionPanelInfo.setLayout(new BorderLayout());
		suggestionPanelInfo.add(suggestions, BorderLayout.NORTH);
		suggestionPanelInfo.add(suggestions2, BorderLayout.CENTER);
		suggestionPanelInfo.add(linkToSuggestion, BorderLayout.SOUTH);

		final JPanel closePanel = new JPanel(new BorderLayout());
		final JButton returnToSetups = new JButton("Return to Setups");
		returnToSetups.addActionListener(e ->
		{
			plugin.setSavedVersionString(plugin.getCurrentVersionString());
			panel.redrawOverviewPanel(true);
		});
		final JLabel clickButtonToLeave = new JLabel("Click here to hide this window");
		final JLabel clickButtonToLeave2 = new JLabel("until the next update");
		clickButtonToLeave.setFont(FontManager.getRunescapeSmallFont());
		clickButtonToLeave2.setFont(FontManager.getRunescapeSmallFont());
		clickButtonToLeave.setHorizontalAlignment(JLabel.CENTER);
		clickButtonToLeave2.setHorizontalAlignment(JLabel.CENTER);
		closePanel.add(clickButtonToLeave, BorderLayout.NORTH);
		closePanel.add(clickButtonToLeave2, BorderLayout.CENTER);
		closePanel.add(returnToSetups, BorderLayout.SOUTH);

		final JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.add(welcomePanel);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		contentPanel.add(latestUpdatePanelInfo);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		contentPanel.add(newUserPanelInfo);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		contentPanel.add(suggestionPanelInfo);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		contentPanel.add(closePanel);

		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10));
		add(contentPanel, BorderLayout.CENTER);
	}


	private JPanel getLatestUpdateInfoPanel()
	{
		final JLabel patchNotesLabel = new JLabel("Patch Notes");
		patchNotesLabel.setFont(FontManager.getRunescapeSmallFont());
		patchNotesLabel.setHorizontalAlignment(JLabel.CENTER);

		final JPanel patchTitlePanel = new JPanel(new BorderLayout());
		patchTitlePanel.add(patchNotesLabel, BorderLayout.NORTH);
		String updateText =     "Sections are here! Sections are a new way to organize your setups. Click the link below to learn more.\n\n" +
								"Support for ToB ornament kits in fuzzy mapping.\n\n" +
								"You can now add a color border to setups. Click 'edit' and select a color with the paint bucket. Right click the paint bucket to remove.\n\n" +
								"Duplicate names for setups are no longer supported. Duplicate names will be fixed upon start up.\n\n" +
								"Names of setups has been limited to 50 characters. Names exceeding this length will be fixed upon start up.";

		JTextArea textArea = new JTextArea(2, 20);
		textArea.setText(updateText);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setFocusable(false);
		textArea.setBackground(ColorScheme.DARK_GRAY_COLOR);
		Font textAreaFont = FontManager.getRunescapeSmallFont();
		//textAreaFont = textAreaFont.deriveFont(textAreaFont.getStyle(), (float)textAreaFont.getSize() - (float)0.1);
		textArea.setFont(textAreaFont);

		textArea.setBorder(new EmptyBorder(0, 0, 0, 0));

		final JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.add(patchTitlePanel, BorderLayout.NORTH);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		contentPanel.add(getJSeparator(ColorScheme.LIGHT_GRAY_COLOR));
		contentPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		contentPanel.add(textArea);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		contentPanel.add(getJSeparator(ColorScheme.LIGHT_GRAY_COLOR));

		final JPanel updatePanel = new JPanel(new BorderLayout());
		updatePanel.add(contentPanel, BorderLayout.CENTER);

		String text = "<html>Click here to learn about sections!</html>";
		JLabel sectionsHyperLink = new JLabel(text);
		sectionsHyperLink.setHorizontalAlignment(SwingConstants.LEFT);
		sectionsHyperLink.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				LinkBrowser.browse("https://github.com/dillydill123/inventory-setups#sections");
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				sectionsHyperLink.setText("<html><a href=''>Click here to learn about sections!</html>");
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				sectionsHyperLink.setText(text);
			}
		});

		JPanel hyperLinkPanel = new JPanel(new BorderLayout());
		hyperLinkPanel.add(sectionsHyperLink, BorderLayout.SOUTH);
		hyperLinkPanel.add(Box.createRigidArea(new Dimension(0 ,8)), BorderLayout.NORTH);
		updatePanel.add(hyperLinkPanel, BorderLayout.SOUTH);

		return updatePanel;
	}

	private JSeparator getJSeparator(Color color)
	{
		JSeparator sep = new JSeparator();
		sep.setBackground(color);
		sep.setForeground(color);
		return sep;
	}

}
