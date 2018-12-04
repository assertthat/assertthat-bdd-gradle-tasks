import com.assertthat.plugins.internal.APIUtil
import com.assertthat.plugins.internal.Arguments
import com.assertthat.plugins.internal.FileUtil
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
class FeaturesTask extends DefaultTask {
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
    String mode = null
    @Input
    @Optional
    String jql = null

    @TaskAction
    def downloadFeatures() {
        Arguments arguments = new Arguments(
                accessKey,
                secretKey,
                projectId,
                proxyURI,
                proxyUsername,
                proxyPassword,
                mode,
                jql
        )
        APIUtil apiUtil = new APIUtil(arguments.getProjectId(), arguments.getAccessKey(), arguments.getSecretKey(), arguments.getProxyURI(), arguments.getProxyUsername(), arguments.getProxyPassword())
        File inZip = apiUtil.download(new File(arguments.getOutputFolder()), mode, jql)
        File zip = new FileUtil().unpackArchive(inZip, new File(arguments.getOutputFolder()))
        zip.delete()
    }
}
