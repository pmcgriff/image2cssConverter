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
package com.rf1m.image2css.util;

import com.rf1m.image2css.domain.SupportedImageType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class ConversionFilenameFilterTest {
    final String imageGifFile = "image.gif";
    final String extensionGif = "gif";

    @Mock
    File directory;

    ConversionFilenameFilter conversionFilenameFilter;

    Set<SupportedImageType> supportedTypes;

    @Before
    public void before(){
        supportedTypes = spy(new HashSet<SupportedImageType>());
        supportedTypes.add(SupportedImageType.gif);
        conversionFilenameFilter = spy(new ConversionFilenameFilter(supportedTypes));
    }

    @Test
    public void shouldReturnTrueWhenFilenameIsSupportedImageType(){
        doReturn(true)
            .when(conversionFilenameFilter)
            .isSupported(extensionGif);

        final boolean result = conversionFilenameFilter.accept(directory, imageGifFile);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfFilenameIsNotSupportedImageType(){
        doReturn(false)
            .when(conversionFilenameFilter)
            .isSupported(extensionGif);

        final boolean result = conversionFilenameFilter.accept(directory, imageGifFile);

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfFilenameIsBlank(){
        final boolean result = conversionFilenameFilter.accept(directory, "");

        assertFalse(result);
    }

    @Test
    public void shouldNotAcceptUnsupportedImageTypeFileExtension(){
        final String unsupportedImageTypeFileExtension = "jpg";

        final boolean result = conversionFilenameFilter.isSupported(unsupportedImageTypeFileExtension);

        assertFalse(result);
    }

    @Test
    public void shouldAcceptSupportedImageTypeFileExtension() {
        final boolean result = conversionFilenameFilter.isSupported(extensionGif);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfUnknownExtensionIsProvided() {
        final String unknown = "unknown";

        final IllegalArgumentException illegalArgumentException = mock(IllegalArgumentException.class);

        doThrow(illegalArgumentException)
            .when(supportedTypes)
            .contains(unknown);

        final boolean result = conversionFilenameFilter.isSupported(unknown);

        assertFalse(result);
    }


}