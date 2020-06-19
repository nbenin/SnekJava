### Preparation for Workshop

---

![Snek](https://media.giphy.com/media/QtZKO7mb7ebpC/giphy.gif)

---

*__Install Oracle JDK (Java Development Kit)__*

1. Make an [Oracle account](https://profile.oracle.com/myprofile/account/create-account.jspx) 
	- free, asks some personal information though
	
2. Download Oracle JDK 11.07(Java Development Kit) tarball from offical site [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
	- jdk-11.0.7_linux-x64_bin.tar.gz

3. Open terminal (ctrl + alt + t)

4. Enter `sudo mkdir /usr/lib/jvm/`
	
5. cd into your Downloads folder, for me its `cd ~/Downloads`

6. Move downloaded tarball to new folder `sudo mv jdk-11.0.7_linux-x64_bin.tar.gz /usr/lib/jvm`

7. Go to the folder you made on step 4 `cd /usr/lib/jvm`

8. Unpack and install tarball `sudo tar zxvf jdk-11.0.7_linux-x64_bin.tar.gz`

9. Remove zip file `sudo rm jdk-11.0.7_linux-x64_bin.tar.gz`

10. Add to your PATH `sudo gedit ~/.bashrc`

11. add `export PATH="/usr/lib/jvm/jdk-11.0.7/bin:$PATH"` to the bottom of the file 
	- Don't forget to do a new line after the "fi"

12. Save and close file, then type `source ~/.bashrc` to reset the bashrc file

13. Enter `java -version` into terminal to verify installation 
	> should say something like 

		java version "11.0.7" 2020-04-14 LTS
		Java(TM) SE Runtime Environment 18.9 (build 11.0.7+8-LTS)
		Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.7+8-LTS, mixed mode)

---

*__Install JetBrains IDEA__*

1. Make sure snap is installed `sudo apt install snapd`

2. Install IDEA `sudo snap install intellij-idea-ultimate --classic`


