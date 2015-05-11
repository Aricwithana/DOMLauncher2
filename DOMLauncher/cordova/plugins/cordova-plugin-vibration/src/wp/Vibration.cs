﻿/*  
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/

using System;
using System.Windows;
using System.Windows.Controls;
using Microsoft.Devices;
using System.Runtime.Serialization;
using System.Threading;
using System.Windows.Resources;
using Microsoft.Phone.Controls;
using System.Diagnostics;


namespace WPCordovaClassLib.Cordova.Commands
{
    public class Vibration : BaseCommand
    {
        private static readonly int DEFAULT_DURATION = 200;

        public void vibrate(string vibrateDuration)
        {
            int msecs = DEFAULT_DURATION; // set default

            try
            {
                string[] args = JSON.JsonHelper.Deserialize<string[]>(vibrateDuration);

                msecs = int.Parse(args[0]);
                if (msecs < 1)
                {
                    msecs = 1;
                }
            }
            catch (FormatException)
            {

            }

            vibrateMs(msecs);

            // TODO: may need to add listener to trigger DispatchCommandResult when the vibration ends...
            DispatchCommandResult();
        }

        private static void vibrateMs(int msecs)
        {
            VibrateController.Default.Start(TimeSpan.FromMilliseconds(msecs));
        }

        public void vibrateWithPattern(string options)
        {
            // falling back to vibrate
            vibrateMs(DEFAULT_DURATION);

            // TODO: may need to add listener to trigger DispatchCommandResult when the vibration ends...
            DispatchCommandResult();
        }

        public void cancelVibration(string options)
        {
            VibrateController.Default.Stop();
            DispatchCommandResult();
        }
    }
}
