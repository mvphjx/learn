package com.hisign.pu.abis.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.project.MavenProject;
import org.jvnet.jaxb2.maven2.AbstractXJC2Mojo;
import org.jvnet.jaxb2.maven2.test.RunXJC2Mojo;

public class TestPlugin extends RunXJC2Mojo
{

    @Override
    public File getSchemaDirectory()
    {
        return new File(getBaseDir(), "src/test/resources");
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected void configureMojo(AbstractXJC2Mojo mojo)
    {
        super.configureMojo(mojo);
        mojo.setProject(new MavenProject());
        mojo.setForceRegenerate(true);
        mojo.setExtension(true);
        mojo.setForceRegenerate(true);
    }

    @Override
    public List<String> getArgs()
    {
        final List<String> args = new ArrayList<String>(super.getArgs());
        args.add("-extension");
        args.add("-Xpuabis");
        return args;
    }

}
