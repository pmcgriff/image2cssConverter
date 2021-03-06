/**
 *
 * Copyright (c) 2011 Matthew D Huckaby. All rights reservered.
 * ------------------------------------------------------------------------------------
 * Image2Css is licensed under Apache 2.0, please see LICENSE file.
 *
 * Use of this software indicates you agree to the following as well :
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * This product includes software developed by The Apache Software Foundation (http://www.apache.org/).
 * ------------------------------------------------------------------------------------
 */
package com.rf1m.image2css.out;

import com.rf1m.image2css.cli.Parameters;
import com.rf1m.image2css.domain.CssClass;

import java.io.IOException;
import java.util.List;

import static java.lang.String.format;

public class ConsoleOutput extends AbstractOutput implements ReportOutput{
    protected final String reportCssTotalTemplate;
    protected final String reportCssFileTemplate;
    protected final String reportHtmlFileTemplate;

    public ConsoleOutput(final String reportCssTotalTemplate,
                         final String reportCssFileTemplate,
                         final String reportHtmlFileTemplate) {

        this.reportCssTotalTemplate = reportCssTotalTemplate;
        this.reportCssFileTemplate = reportCssFileTemplate;
        this.reportHtmlFileTemplate = reportHtmlFileTemplate;
    }

    @Override
    public void out(final Parameters parameters, final List<CssClass> cssClasses) throws IOException {
        if(isValidParametersAndClassesWithConsoleOutput(parameters, cssClasses)){
			for(final CssClass cssClass : cssClasses){
				this.println(cssClass.getBody());
			}
		}
    }

    @Override
    public void generateReportOutput(final Parameters parameters, final List<CssClass> cssClasses){
        if(super.isValidParametersAndClasses(parameters, cssClasses)){
            if(!parameters.isOutputToConsoleDesired()) {
                this.println(format(reportCssTotalTemplate, cssClasses.size()));
            }
            if(null != parameters.getCssFile()){
                this.println(format(reportCssFileTemplate, parameters.getCssFile().getName()));
            }

            if(null != parameters.getHtmlFile()){

                this.println(format(reportHtmlFileTemplate, parameters.getHtmlFile().getName()));
            }
        }
	}

    protected boolean isValidParametersAndClassesWithConsoleOutput(final Parameters parameters, final List<CssClass> cssClasses) {
        return super.isValidParametersAndClasses(parameters, cssClasses) && parameters.isOutputToConsoleDesired();
    }

    protected void println(final String out) {
        System.out.println(out);
    }

}
