import com.assertthat.plugins.internal.APIUtil
import com.assertthat.plugins.internal.Arguments
import com.assertthat.plugins.internal.FileUtil
import org.apache.maven.plugin.MojoExecutionException
import org.codehaus.jettison.json.JSONException
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction

/**
 * Copyright (c) 2018 AssertThat
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * <p>
 * Created by Glib_Briia on 15/05/2018.
 */
class ReportTask extends DefaultTask {
    @Input
    String projectId
    @Input
    @Optional
    String secretKey = null
    @Input
    @Optional
    String accessKey = null
    @Input
    @Optional
    String proxyURI = null
    @Input
    @Optional
    String proxyUsername = null
    @Input
    @Optional
    String proxyPassword = null
    @Input
    @Optional
    String runName = null
    @Input
    @Optional
    String jsonReportFolder = null
    @Input
    @Optional
    String jsonReportIncludePattern = null
    @Input
    @Optional
    String type = null

    @TaskAction
    def submitReport() {
        Arguments arguments = new Arguments(accessKey,
                secretKey,
                projectId,
                runName,
                null,
                jsonReportFolder,
                jsonReportIncludePattern,
                proxyURI,
                proxyUsername,
                proxyPassword,
                null,
                null,
                type)
        APIUtil apiUtil = new APIUtil(arguments.getProjectId(), arguments.getAccessKey(), arguments.getSecretKey(), arguments.getProxyURI(), arguments.getProxyUsername(), arguments.getProxyPassword())

        String[] files = new FileUtil().findJsonFiles(new File(arguments.getJsonReportFolder()), arguments.getJsonReportIncludePattern(), null)
        Long runid = -1L
        for (String f : files) {
            try {
                runid = apiUtil.upload(runid, arguments.getRunName(), arguments.getJsonReportFolder() + f, type)
            } catch (IOException e) {
                throw new MojoExecutionException("Failed to upload report", e);
            } catch (JSONException e) {
                throw new MojoExecutionException("Failed to upload report", e);
            }
        }
    }
}
