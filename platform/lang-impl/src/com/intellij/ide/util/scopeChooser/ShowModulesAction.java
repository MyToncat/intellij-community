/*
 * Copyright 2000-2009 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intellij.ide.util.scopeChooser;

import com.intellij.icons.AllIcons;
import com.intellij.ide.IdeBundle;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.packageDependencies.DependencyUISettings;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
public final class ShowModulesAction extends ToggleAction {
  private final Runnable myUpdate;

  public ShowModulesAction(final Runnable update) {
    super(IdeBundle.message("action.show.modules"),
          IdeBundle.message("action.description.show.modules"), AllIcons.Actions.GroupByModule);
    myUpdate = update;
  }

  @Override
  public boolean isSelected(@NotNull AnActionEvent event) {
    return DependencyUISettings.getInstance().UI_SHOW_MODULES;
  }
  @Override
  public @NotNull ActionUpdateThread getActionUpdateThread() {
    return ActionUpdateThread.BGT;
  }
  @Override
  public void setSelected(@NotNull AnActionEvent event, boolean flag) {
    DependencyUISettings.getInstance().UI_SHOW_MODULES = flag;
    myUpdate.run();
  }
}
