import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.BaseRepositoryBuilder;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import com.jcraft.jsch.*;
import com.jcraft.jsch.Session;


public class git_clone {

public static void main(String[] args) throws IOException, IllegalStateException, GitAPIException, InterruptedException {
	File PATH = new File("/Users/1100684/Test_repo");
	String url = "https://github.com/saljapur/";
	FileRepositoryBuilder builder = new FileRepositoryBuilder();
	Repository repository = builder.setGitDir(PATH).readEnvironment().findGitDir().build();

	@SuppressWarnings("resource")
	Git git = new Git(repository);
	Config cfg = git.getRepository().getConfig();
	String name = cfg.getString("user", null, "name");
	System.out.println("Config output : " + name);
	
	cfg.setString("http", null,"proxy","http://PITC-Zscaler-Americas-Alpharetta3PR.proxy.corporate.ge.com:80");
	cfg.setString("https", null,"sslVerify", "false");
	cfg.setString("http", "https://github.com","sslVerify", "false");
	System.out.println("Set Config Successful");
	String name1 = cfg.getString("http", null, "proxy");
	System.out.println("Set Config output : " + name1);
	System.out.println("Git Dir : " + git.getRepository().getDirectory());

	@SuppressWarnings("static-access")
	CloneCommand clone = git.cloneRepository();
	clone.setBare(false);
	clone.setCloneAllBranches(true);
	clone.setDirectory(PATH).setURI(url);
	UsernamePasswordCredentialsProvider user = new UsernamePasswordCredentialsProvider("saljapur","Password");                
	clone.setCredentialsProvider(user);
	clone.call(); 
	
}
}
