// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.model.data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Class representing a conversation, which can be thought of as a chat room. Conversations are
 * created by a User and contain Messages.
 */
public class Conversation {
  public final UUID id;
  public final UUID owner;
  public final Instant creation;
  public final String title;
  public final boolean isPrivate;
  public final List<UUID> participants;


  /**
   * Constructs a new Conversation.
   *
   * @param id the ID of this Conversation
   * @param owner the ID of the User who created this Conversation
   * @param title the title of this Conversation
   * @param creation the creation time of this Conversation
   * @param isPrivate whether the Conversation is private
   * @param participants the IDs of the Users in this private Conversation. Null if is public.
   */
  public Conversation(UUID id, UUID owner, String title, Instant creation, boolean isPrivate, List<UUID> participants) {
    this.id = id;
    this.owner = owner;
    this.creation = creation;
    this.title = title;
    this.isPrivate = isPrivate;
    this.participants = participants;
  }

  /** Returns the ID of this Conversation. */
  public UUID getId() {
    return id;
  }

  /** Returns the ID of the User who created this Conversation. */
  public UUID getOwnerId() {
    return owner;
  }

  /** Returns the title of this Conversation. */
  public String getTitle() {
    return title;
  }

  /** Returns the creation time of this Conversation. */
  public Instant getCreationTime() {
    return creation;
  }

  /** Returns true if this Conversation is private. */
  public boolean isPrivate() {
    return isPrivate;
  }

  /** Returns the IDs of the Users in this private Conversation. */
  public List<UUID> getParticipants() {
    return participants;
  }

  /** Returns true if the user is a participant of this private Conversation. */
  public boolean hasParticipant(UUID user) {
    if (participants == null){
      return false;
    }
    for(UUID participant : participants){
      if (user.equals(participant)){
        return true;
      }
    }
    return false;
  }
}
