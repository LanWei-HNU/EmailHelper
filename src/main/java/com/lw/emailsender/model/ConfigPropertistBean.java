package com.lw.emailsender.model;

/**
 * 用户邮箱信息类
 * @author LanWei-HNU
 *
 */
public class ConfigPropertistBean {


	private String email;
	private String password;
	private String hostName;
	private String protocol;
	private String displayName;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String toString() {
		return "ConfigPropertistBean: hostName= " + hostName + "  protocol= " + protocol + "  email= " + email
				+ "  password= " + password + "  displayName= " + displayName;
	}

	private static ConfigPropertistBean instance = new ConfigPropertistBean();

	private ConfigPropertistBean() {

	}

/*	public static void init() {
		File configFile = new File(Constant.CONFIG_FILE_PATH);
		System.out.println("配置文件：" + configFile.getAbsolutePath());
		if (configFile.exists()) {
			*//**** 配置文件存在 ****//*
			BufferedReader bufReader;
			InputStreamReader isr;
			try {
				//设置编码
				isr = new InputStreamReader(new FileInputStream(configFile), "GB2312");
				bufReader = new BufferedReader(isr);
				Map<String, String> hashMap = new HashMap<String, String>();
				String temp = null;
				while ((temp = bufReader.readLine()) != null) {
					String[] ss = temp.split(":");
					if (ss.length == 2) {
						hashMap.put(ss[0], ss[1]);
					}
				}
				bufReader.close();
				isr.close();
				// 通过反射赋值
				ClassRefUtil.setFieldValue(instance, hashMap);
			} catch (IOException e) {
				log.error("配置文件读取异常");
			}

		} else {
			*//**** 配置文件不存在 ********//*
			// 先创建文件所在的目录
			configFile.getParentFile().mkdirs();
			// 创建新文件
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				System.out.println("创建新文件时出错");
				e.printStackTrace();
			}
		}
	}*/

	public static ConfigPropertistBean getInstance() {
		return instance;
	}
}
