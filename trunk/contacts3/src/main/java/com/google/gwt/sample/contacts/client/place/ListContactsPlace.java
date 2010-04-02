/**
 * Copyright 2010 Yaakov Chaikin (yaakov.chaikin@gmail.com) Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed
 * to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the
 * License.
 */
package com.google.gwt.sample.contacts.client.place;

import java.util.List;
import java.util.Map;

import org.tbiq.gwt.tools.placeservice.browser.HistoryTokenParser;
import org.tbiq.gwt.tools.placeservice.browser.Place;
import org.tbiq.gwt.tools.placeservice.browser.PlaceServiceUtil;
import org.tbiq.gwt.tools.presenter.browser.Presenter;
import org.tbiq.gwt.tools.rpcservice.browser.RpcServiceAsync;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.sample.contacts.client.Contacts;
import com.google.gwt.sample.contacts.client.presenter.ContactsPresenter;
import com.google.gwt.sample.contacts.client.view.ContactsView;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * ListContactsPlace class represents the view that lists all the contacts.
 * 
 * @author Yaakov Chaikin (yaakov.chaikin@gmail.com)
 */
public class ListContactsPlace
  implements Place
{
  /** View ID of this place. */
  public static final String VIEW_ID = "list";

  /** Class version ID. */
  private static final long serialVersionUID = -548712836311564858L;

  /** Flag if the history token of this place should be added to the browser URL. */
  private boolean toBeAddedToBrowserHistory;

  /**
   * History token parser which knows the format to use when building history token based
   * on this place's data.
   */
  private HistoryTokenParser historyTokenParser;


  /**
   * Constructor.
   * 
   * @param toBeAddedToBrowserHistory Flag if the history token of this place should be
   *          added to the browser URL.
   */
  public ListContactsPlace(boolean toBeAddedToBrowserHistory)
  {
    this.toBeAddedToBrowserHistory = toBeAddedToBrowserHistory;
  }

  @Override
  public Place createPlace(Map<String, List<String>> nameValuePairs,
                           boolean toBeAddedToBrowserHistory)
  {
    return new ListContactsPlace(toBeAddedToBrowserHistory);
  }

  @Override
  public String getHistoryToken()
  {
    return historyTokenParser.buildHistoryToken("",
                                                historyTokenParser.getViewIdParam(),
                                                VIEW_ID);
  }

  @Override
  public String getViewId()
  {
    return VIEW_ID;
  }

  @Override
  public boolean isToBeAddedToBrowserHistory()
  {
    return toBeAddedToBrowserHistory;
  }

  @Override
  public void setToBeAddedToBrowserHistory(boolean toBeAddedToBrowserHistory)
  {
    this.toBeAddedToBrowserHistory = toBeAddedToBrowserHistory;
  }

  @Override
  public void show(HasWidgets container, HandlerManager eventBus)
  {
    // Add history token to URL if so indicated
    if (toBeAddedToBrowserHistory)
    {
      PlaceServiceUtil.addToBrowserHistory(this);
    }

    // Initialize async service needed for the presenter
    RpcServiceAsync rpcService = Contacts.RPC_SERVICE;

    // Create presenter and execute
    Presenter listContactsPresenter = new ContactsPresenter(rpcService, eventBus,
      new ContactsView());
    listContactsPresenter.go(container);
  }

  @Override
  public void setHistoryTokenParser(HistoryTokenParser historyTokenParser)
  {
    this.historyTokenParser = historyTokenParser;
  }
}