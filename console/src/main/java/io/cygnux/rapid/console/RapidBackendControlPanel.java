package io.cygnux.rapid.console;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class RapidBackendControlPanel extends JFrame {

    // 按你原来的后端基础地址来改
    private static final String BASE_URL = "http://localhost:8080";

    public RapidBackendControlPanel() {
        super("Rapid Backend Settings");
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        // 顶部标题：-> 控制面板
        JLabel titleLabel = new JLabel("-> 控制面板");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(8, 8, 0, 8));
        add(titleLabel, BorderLayout.NORTH);

        // 中间“表格”区域
        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // ===== 表头行：策略 / 交易 / 系统 / 监控 =====
        gbc.gridy = 0;

        gbc.gridx = 0;
        tablePanel.add(createHeaderLabel("策略"), gbc);

        gbc.gridx = 1;
        tablePanel.add(createHeaderLabel("交易"), gbc);

        gbc.gridx = 2;
        tablePanel.add(createHeaderLabel("系统"), gbc);

        gbc.gridx = 3;
        tablePanel.add(createHeaderLabel("监控"), gbc);

        // ===== 第一行链接 =====
        gbc.gridy = 1;

        gbc.gridx = 0;
        tablePanel.add(createLinkButton("策略管理", "/strategy/ui"), gbc);

        gbc.gridx = 1;
        tablePanel.add(createLinkButton("Adaptor管理", "/adapter/ui"), gbc);

        gbc.gridx = 2;
        tablePanel.add(createLinkButton("系统指令", null), gbc);

        gbc.gridx = 3;
        tablePanel.add(createLinkButton("日志数据", null), gbc);

        // ===== 第二行链接 =====
        gbc.gridy = 2;

        gbc.gridx = 0;
        tablePanel.add(createLinkButton("策略参数", "/strategy/ui/param"), gbc);

        gbc.gridx = 1;
        tablePanel.add(createLinkButton("交易参数", "/adapter/ui/param"), gbc);

        gbc.gridx = 2;
        tablePanel.add(createLinkButton("用户管理", null), gbc);

        gbc.gridx = 3;
        tablePanel.add(createLinkButton("订单数据", null), gbc);

        // ===== 第三行链接 =====
        gbc.gridy = 3;

        gbc.gridx = 0;
        tablePanel.add(createEmptyCell(), gbc);

        gbc.gridx = 1;
        tablePanel.add(createLinkButton("交易账户管理", null), gbc);

        gbc.gridx = 2;
        tablePanel.add(createLinkButton("动态修改参数", null), gbc);

        gbc.gridx = 3;
        tablePanel.add(createLinkButton("盈亏详情", null), gbc);

        // ===== 第四行链接 =====
        gbc.gridy = 4;

        gbc.gridx = 0;
        tablePanel.add(createEmptyCell(), gbc);

        gbc.gridx = 1;
        tablePanel.add(createLinkButton("启动交易", null), gbc);

        gbc.gridx = 2;
        tablePanel.add(createEmptyCell(), gbc);

        gbc.gridx = 3;
        tablePanel.add(createEmptyCell(), gbc);

        // ===== 最后一行：列表数据显示说明（跨全部列）=====
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel descPanel = new JPanel();
        descPanel.setLayout(new BoxLayout(descPanel, BoxLayout.Y_AXIS));
        descPanel.setBorder(BorderFactory.createTitledBorder("列表数据显示说明"));

        JLabel line1 = new JLabel(
                "用户名(用户ID)   |   策略名(策略ID/策略类型)   |   算法名(算法ID/算法类型)   |   Adaptor名(AdaptorID/Adaptor类型)");
        line1.setFont(line1.getFont().deriveFont(Font.PLAIN, 11f));

        descPanel.add(line1);

        tablePanel.add(descPanel, gbc);

        add(tablePanel, BorderLayout.CENTER);

        // 默认窗口大小和居中
        setPreferredSize(new Dimension(800, 400));
        pack();
        setLocationRelativeTo(null);
    }

    private JLabel createHeaderLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.BOLD, font.getSize() + 1f));
        label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        return label;
    }

    private JComponent createEmptyCell() {
        // 占位用的空 Panel, 让布局整齐
        return new JPanel();
    }

    private JButton createLinkButton(String text, String path) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);

        button.addActionListener(e -> {
            if (path == null || path.isBlank() || "#".equals(path)) {
                // 没有具体实现的功能, 你可以在这里改成真正的业务调用
                JOptionPane.showMessageDialog(
                        this,
                        "功能[" + text + "]尚未实现(原来是 href=\"#\"). \n请在此处添加实际逻辑.",
                        "提示",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                openInBrowser(BASE_URL + path);
            }
        });

        return button;
    }

    private void openInBrowser(String url) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "当前环境不支持自动打开浏览器. \n请手动访问: " + url,
                        "无法打开浏览器",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "打开链接失败: " + ex.getMessage() + "\nURL: " + url,
                    "错误",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RapidBackendControlPanel frame = new RapidBackendControlPanel();
            frame.setVisible(true);
        });
    }
}
